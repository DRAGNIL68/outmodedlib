package net.outmoded.outmodedlib.packer.jsonObjects.unicode.providers;

public record UnihexSizeOverrides(String from, String to, int left, int right){
    public UnihexSizeOverrides(String from, String to, int left, int right){
        this.from = from;
        this.to = to;
        this.left = left;
        this.right = right;

    }

}
