# StreamUpdaterVersion4
Control a stream like never before, customize and load custom Thumbnails, Capture and automatically render videos, and Upload straight to YouTube.

Minimum Requirements
2GHz processor or higher, to handle streaming with OBS or any other streaming platform.
4GB RAM or higher, to handle streaming platforms.
10GB of space or higher, to handle OBS file output and custom images produced from the Stream Updater.

Recommended Requirements
3GHz processor
8GB RAM
50GB of space

How to use:
1. Download the project and compile using Eclipse or any other method to compile the Java code.
    * Requests can be made for a precompiled code, feel free to ask!
2. Launch by just simply running the *.jar
    * Recommended that you run via Command Line, use "java -jar MY_JAR.jar"
3. Automatic start up will launch and scan for missing directories and files, then it will build those.
4. Close the program, inside C:/Users/YOUR_NAME/Documents/StreamUpdater/* will contain the files.
    * Follow the directions below then continue.
5. Once all the files and linking is setup, relaunch the Stream Updater from via jar or CMD.
6. Adding Players/Commentators/Sponsors are done by the first module, Tournament Enlisting Tab.
7. The Stream Updater module, updates the stream. By clicking 'Singles', 'Doubles' will then appear.
    * Do note, doulbes is ran from Team One being on top, Team Two being on bottom. Left controls Team 1 and Right controls Team 2.
8. Stream Capture module captures the stream but requires an FLV input stream first.
9. The Thumbnail Editor allows you to customize the thumbnails all the while saving and loading previous versions.
10. Lastly the Uploading Tab allows you to upload straight to YouTube with provided description, tags, and finally privacy.

Setting up the files:

Game/*

    1. This directory handles the custom character models/icons, commentators, players, and sponsor.
   
    2. Simply copy your characters into the Characters folder.
   
    3. Everything else will be built as you use.

Media/*
    1. This directory handles the file output of the videos and thumbnails, don't worry about this.

Saves/*

    1. This directory handles the save objects that the program will produce to save your video timestamps and thumbnails.

Templates/*

    1. This directory handles the templates created from the Thumbnail Editor module
    
Text/*

    1. The main "engine" of the program lies in this directory, this handles file output manipulation.
    2. Simply link your stream to these files inside.
    
Uploading/*
  
    1. This directory was first used for the client secrets but google seems to not care all too much about it.

Thats It!!!
Quite simple, enjoy.
