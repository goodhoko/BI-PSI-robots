package cz.goodhoko.tcp;


public enum Orientation {
    NORTH("^"),
    EAST(">"),
    SOUTH("v"),
    WEST("<"){
        @Override
        public Orientation next() {
            return values()[0];
        };
    };

    private final String text;

    Orientation(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public Orientation next() {
        return values()[ordinal() + 1];
    }
}
