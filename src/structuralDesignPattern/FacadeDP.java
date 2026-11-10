package structuralDesignPattern;

public class FacadeDP {
    public static void main(String[] args) {
        MediaFacade mediaFacade = new MediaFacade();
        System.out.println("Welcome to Multimedia App!");
        System.out.println("Choose an action: playMusic, playVideo, viewImage");
        String action = "playmusic";
        mediaFacade.performAction(action);
    }
}

class MediaFacade {
    private MusicPlayer musicPlayer;
    private VideoPlayer videoPlayer;
    private ImageViewer imageViewer;
    public MediaFacade() {
        this.musicPlayer = new MusicPlayer();
        this.videoPlayer = new VideoPlayer();
        this.imageViewer = new ImageViewer();
    }
    public void performAction(String action) {
        switch (action.toLowerCase()) {
            case "playmusic":
                musicPlayer.initializeAudioDrivers();
                musicPlayer.decodeAudio();
                musicPlayer.startPlayback();
                break;
            case "playvideo":
                videoPlayer.setupRenderingEngine();
                videoPlayer.loadVideoFile();
                videoPlayer.playVideo();
                break;
            case "viewimage":
                imageViewer.loadImageFile();
                imageViewer.applyScaling();
                imageViewer.displayImage();
                break;
            default:
                System.out.println("Invalid action!");
        }
    }
}

class ImageViewer {
    public void loadImageFile() {
        System.out.println("Image file loaded.");
    }
    public void applyScaling() {
        System.out.println("Image scaled.");
    }
    public void displayImage() {
        System.out.println("Image displayed.");
    }
}

class VideoPlayer {
    public void setupRenderingEngine() {
        System.out.println("Rendering engine set up.");
    }
    public void loadVideoFile() {
        System.out.println("Video file loaded.");
    }
    public void playVideo() {
        System.out.println("Video playback started.");
    }
}

class MusicPlayer {
    public void initializeAudioDrivers() {
        System.out.println("Audio drivers initialized.");
    }
    public void decodeAudio() {
        System.out.println("Audio decoded.");
    }
    public void startPlayback() {
        System.out.println("Music playback started.");
    }
}