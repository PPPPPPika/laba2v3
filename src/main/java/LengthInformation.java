public enum LengthInformation {
    SHORT{
        int getValue(){
            return 100;
        }
    },
    MIDDLE{
        int getValue(){
            return 500;
        }
    },
    LONG{
        int getValue(){
            return 1000;
        }
    };
    abstract int getValue();
}
