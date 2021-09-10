public enum KeyWords {
    KEY_WORD1{
        String getValue(){
            return "экономика";
        }
    },
    KEY_WORD2{
        String getValue(){
            return "финансы";
        }
    },
    KEY_WORD3{
        String getValue(){
            return "акции";
        }
    },
    KEY_WORD4{
        String getValue(){
            return "облигации";
        }
    },
    KEY_WORD5{
        String getValue(){
            return "фьючерсы";
        }
    },
    KEY_WORD6{
        String getValue(){
            return "инвестирование";
        }
    },
    KEY_WORD7{
        String getValue(){
            return "биржа";
        }
    },
    KEY_WORD8{
        String getValue(){
            return "капитал";
        }
    },
    KEY_WORD9{
        String getValue(){
            return "бизнес";
        }
    };
    abstract String getValue();
}
