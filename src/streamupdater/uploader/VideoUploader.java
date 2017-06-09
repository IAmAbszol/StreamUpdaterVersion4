package streamupdater.uploader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTube.Thumbnails.Set;
import com.google.api.services.youtube.model.ThumbnailSetResponse;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;
import com.google.common.collect.Lists;

import streamupdater.files.FileManager;

public class VideoUploader {
	
	private static YouTube youtube;
	
	private final String VIDEO_FORMAT = "video/*";
	private final String IMAGE_FORMAT = "image/png";
	
	private String videoId = null;
	private String thumbnail = null;
	
	public VideoUploader() {
		
		if(!new File(FileManager.getUploadingDirectory() + "/client_secret.json").exists()) 
			 if (JOptionPane.showConfirmDialog(null, 
			            "Warning! client_secret.json was not detected inside the StreamUpdater/Uploading directory.\nAre you sure you want to continue?", "Client Secrets For Youtube",
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION)
			            System.exit(0);
		
	}
	
	private boolean checkClient() {
		if(!new File(FileManager.getUploadingDirectory() + "/client_secret.json").exists()) 
			 JOptionPane.showMessageDialog(null, 
			            "Warning! client_secret.json was not detected inside the StreamUpdater/Uploading directory.\nPlease fix then try again=]");
		return new File(FileManager.getUploadingDirectory() + "/client_secret.json").exists();
	}
	
	public void upload(String fileName, String thumbnailName, String view, ArrayList<String> tags, String description) {
		
		if(!checkClient()) return;
		
		List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.upload");
		
		try {
			
			Credential creds = Auth.authorize(scopes, "uploadvideo");
			
			youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, creds).setApplicationName("youtube-uploader").build();
			
			Video videoObjectDefiningMetadata = new Video();
			
			VideoStatus status = new VideoStatus();
			status.setPrivacyStatus(view);
			videoObjectDefiningMetadata.setStatus(status);
			
			VideoSnippet snippet = new VideoSnippet();
			snippet.setTitle(fileName.replace(".mp4", "").replaceAll(".*/", ""));
			snippet.setDescription(description);
			snippet.setTags(tags);
			videoObjectDefiningMetadata.setSnippet(snippet);
			
			InputStream is = new FileInputStream(FileManager.getMediaDirectory() + "/" + fileName);
			System.out.println("Video status -> "+is);
			
			InputStreamContent mediaContent = new InputStreamContent(VIDEO_FORMAT,
	                    is);

			
			 YouTube.Videos.Insert videoInsert = youtube.videos()
	                    .insert("snippet,statistics,status", videoObjectDefiningMetadata, mediaContent);
			 
			// Set the upload type and add an event listener.
	            MediaHttpUploader uploader = videoInsert.getMediaHttpUploader();

	            // Indicate whether direct media upload is enabled. A value of
	            // "True" indicates that direct media upload is enabled and that
	            // the entire media content will be uploaded in a single request.
	            // A value of "False," which is the default, indicates that the
	            // request will use the resumable media upload protocol, which
	            // supports the ability to resume an upload operation after a
	            // network interruption or other transmission failure, saving
	            // time and bandwidth in the event of network failures.
	            uploader.setDirectUploadEnabled(false);

	            MediaHttpUploaderProgressListener progressListener = new MediaHttpUploaderProgressListener() {
	                public void progressChanged(MediaHttpUploader uploader) throws IOException {
	                    switch (uploader.getUploadState()) {
	                        case INITIATION_STARTED:
	                            System.out.println("Initiation Started");
	                            break;
	                        case INITIATION_COMPLETE:
	                            System.out.println("Initiation Completed");
	                            break;
	                        case MEDIA_IN_PROGRESS:
	                            System.out.println("Upload in progress");
	                            break;
	                        case MEDIA_COMPLETE:
	                            System.out.println("Upload Completed!");
	                            break;
	                        case NOT_STARTED:
	                            System.out.println("Upload Not Started!");
	                            break;
	                    }
	                }
	            };
	            uploader.setProgressListener(progressListener);

	            // Call the API and upload the video.
	            Video returnedVideo = videoInsert.execute();

	            // Print data about the newly inserted video from the API response.
	            System.out.println("\n================== Returned Video ==================\n");
	            System.out.println("  - Id: " + returnedVideo.getId());
	            System.out.println("  - Title: " + returnedVideo.getSnippet().getTitle());
	            System.out.println("  - Tags: " + returnedVideo.getSnippet().getTags());
	            System.out.println("  - Privacy Status: " + returnedVideo.getStatus().getPrivacyStatus());
	            System.out.println("  - Video Count: " + returnedVideo.getStatistics().getViewCount());
	            
	            videoId = returnedVideo.getId();
	            thumbnail = thumbnailName;
	            
	            uploadThumbnail();
			
		} catch (GoogleJsonResponseException e) {
            System.err.println("GoogleJsonResponseException code: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
        } catch (Throwable t) {
            System.err.println("Throwable: " + t.getMessage());
            t.printStackTrace();
        }
		
	}
	
	private void uploadThumbnail() {
		
		// This OAuth 2.0 access scope allows for full read/write access to the
        // authenticated user's account.
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");

        try {
            // Authorize the request.
            Credential credential = Auth.authorize(scopes, "uploadthumbnail");

            // This object is used to make YouTube Data API requests.
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential).setApplicationName(
                    "youtube-cmdline-uploadthumbnail-sample").build();

            // Prompt the user to enter the video ID of the video being updated.
            String videoId = this.videoId;
            System.out.println("You chose " + videoId + " to upload a thumbnail.");

            // Prompt the user to specify the location of the thumbnail image.
            File imageFile = new File(FileManager.getMediaDirectory() + "/" + thumbnail);
            System.out.println("You chose " + imageFile + " to upload.");

            // Create an object that contains the thumbnail image file's
            // contents.
            InputStreamContent mediaContent = new InputStreamContent(
                    IMAGE_FORMAT, new BufferedInputStream(new FileInputStream(imageFile)));
            mediaContent.setLength(imageFile.length());

            // Create an API request that specifies that the mediaContent
            // object is the thumbnail of the specified video.
            Set thumbnailSet = youtube.thumbnails().set(videoId, mediaContent);

            // Set the upload type and add an event listener.
            MediaHttpUploader uploader = thumbnailSet.getMediaHttpUploader();

            // Indicate whether direct media upload is enabled. A value of
            // "True" indicates that direct media upload is enabled and that
            // the entire media content will be uploaded in a single request.
            // A value of "False," which is the default, indicates that the
            // request will use the resumable media upload protocol, which
            // supports the ability to resume an upload operation after a
            // network interruption or other transmission failure, saving
            // time and bandwidth in the event of network failures.
            uploader.setDirectUploadEnabled(false);

            // Set the upload state for the thumbnail image.
            MediaHttpUploaderProgressListener progressListener = new MediaHttpUploaderProgressListener() {
                @Override
                public void progressChanged(MediaHttpUploader uploader) throws IOException {
                    switch (uploader.getUploadState()) {
                        // This value is set before the initiation request is
                        // sent.
                        case INITIATION_STARTED:
                            System.out.println("Initiation Started");
                            break;
                        // This value is set after the initiation request
                        //  completes.
                        case INITIATION_COMPLETE:
                            System.out.println("Initiation Completed");
                            break;
                        // This value is set after a media file chunk is
                        // uploaded.
                        case MEDIA_IN_PROGRESS:
                            System.out.println("Upload in progress");
                            System.out.println("Upload percentage: " + uploader.getProgress());
                            break;
                        // This value is set after the entire media file has
                        //  been successfully uploaded.
                        case MEDIA_COMPLETE:
                            System.out.println("Upload Completed!");
                            break;
                        // This value indicates that the upload process has
                        //  not started yet.
                        case NOT_STARTED:
                            System.out.println("Upload Not Started!");
                            break;
                    }
                }
            };
            uploader.setProgressListener(progressListener);

            // Upload the image and set it as the specified video's thumbnail.
            ThumbnailSetResponse setResponse = thumbnailSet.execute();

            // Print the URL for the updated video's thumbnail image.
            System.out.println("\n================== Uploaded Thumbnail ==================\n");
            System.out.println("  - Url: " + setResponse.getItems().get(0).getDefault().getUrl());

        } catch (GoogleJsonResponseException e) {
            System.err.println("GoogleJsonResponseException code: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
            e.printStackTrace();

        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
        }
		
	}
	
	public void uploadAll(ArrayList<String> fileName, ArrayList<String> images, String view, ArrayList<String> tags, String description) {
		if(!checkClient()) return;
		for(int i = 0; i < fileName.size(); i++) {

			upload(fileName.get(i), images.get(i), view, tags, description);
			
		}
	}
		
}
