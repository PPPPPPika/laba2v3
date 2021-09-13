public enum KeyWords {
    KEY_WORD1{
        String getValue(){
            return "экономика это";
        }
    },
    KEY_WORD2{
        String getValue(){
            return "финансы это";
        }
    },
    KEY_WORD3{
        String getValue(){
            return "акции это";
        }
    },
    KEY_WORD4{
        String getValue(){
            return "облигации это";
        }
    },
    KEY_WORD5{
        String getValue(){
            return "фьючерсы это";
        }
    },
    KEY_WORD6{
        String getValue(){
            return "инвестирование это";
        }
    },
    KEY_WORD7{
        String getValue(){
            return "биржа это";
        }
    },
    KEY_WORD8{
        String getValue(){
            return "капитал это";
        }
    },
    KEY_WORD9{
        String getValue(){
            return "бизнес это";
        }
    },
    KEY_WORD10{
        String getValue() { return "финансовый рынок это"; }
    },
    KEY_WORD11{
        String getValue() { return "банковские вклады это"; }
    },
    KEY_WORD12{
        String getValue(){ return "сложный процент это"; }
    },
    KEY_WORD13{
        String getValue(){ return "центральный банк"; }
    },
    KEY_WORD14{
        String getValue(){ return "налоги это"; }
    };
    abstract String getValue();
}