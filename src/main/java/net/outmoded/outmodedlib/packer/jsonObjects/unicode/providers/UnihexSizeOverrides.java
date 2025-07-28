package net.outmoded.outmodedlib.packer.jsonObjects.unicode.providers;

public record UnihexSizeOverrides(String from, String to, double left, double right){
    public UnihexSizeOverrides(String from, String to, double left, double right){
        this.from = from;
        this.to = to;
        this.left = left;
        this.right = right;

    }

}
