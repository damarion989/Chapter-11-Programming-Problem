public class Main {
    public static void main(String[] args) {
        // Phase 1: Remote Proxy
        System.out.println("=== Remote Proxy ===");

        RealMedia remoteMedia = new RealMedia("Ocean Adventure", 180);
        MediaItem remoteProxy = new RemoteMediaProxy(remoteMedia);

        System.out.println("Title: " + remoteProxy.getTitle());
        System.out.println("Duration: " + remoteProxy.getDuration() + " seconds");
        remoteProxy.play();
        remoteProxy.play();

        // Phase 2: Virtual Proxy
        System.out.println("\n=== Virtual Proxy ===");

        MediaItem virtualProxy =
                new VirtualMediaProxy("Mountain Journey", 240);

        System.out.println("Virtual proxy created. Assets are not loaded yet.");

        System.out.println("Title: " + virtualProxy.getTitle());
        System.out.println("Duration: " + virtualProxy.getDuration() + " seconds");
        virtualProxy.play();
        virtualProxy.play();
    }
}

interface MediaItem {
    String getTitle();
    int getDuration();
    void play();
}

class RealMedia implements MediaItem {
    private String title;
    private int duration;

    public RealMedia(String title, int duration) {
        this.title = title;
        this.duration = duration;

        System.out.println(
                "Establishing connection to remote server... [Success]");
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void play() {
        System.out.println("Playing: " + title);
    }
}

class RemoteMediaProxy implements MediaItem {
    private RealMedia realMedia;
    private boolean isConnected = false;

    public RemoteMediaProxy(RealMedia realMedia) {
        this.realMedia = realMedia;
    }

    private void checkConnection() {
        if (!isConnected) {
            System.out.println(
                    "Remote proxy: establishing remote connection... [Success]");
            isConnected = true;
        }
    }

    @Override
    public String getTitle() {
        checkConnection();
        return realMedia.getTitle();
    }

    @Override
    public int getDuration() {
        checkConnection();
        return realMedia.getDuration();
    }

    @Override
    public void play() {
        checkConnection();
        realMedia.play();
    }
}

class VirtualMediaProxy implements MediaItem {
    private RealMedia realMedia = null;
    private String title;
    private int duration;

    public VirtualMediaProxy(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    private void loadMedia() {
        if (realMedia == null) {
            System.out.println("Loading heavy assets into memory...");
            realMedia = new RealMedia(title, duration);
        }
    }

    @Override
    public String getTitle() {
        loadMedia();
        return realMedia.getTitle();
    }

    @Override
    public int getDuration() {
        loadMedia();
        return realMedia.getDuration();
    }

    @Override
    public void play() {
        loadMedia();
        realMedia.play();
    }
}
