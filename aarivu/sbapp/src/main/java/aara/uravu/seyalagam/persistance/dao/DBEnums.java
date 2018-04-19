package aara.uravu.seyalagam.persistance.dao;

public class DBEnums{

    public enum NodeType {
        CASTE("C-"),
        PERSON("P-"),
        EVENT_MARRIAGE ("E_M-"),
        LOCATION("L-");

        private String key;

        NodeType(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    public enum RelationType{

        //Uravugal
        MANAMAGAN,MANAMAGAL,KUZHANTHAI,

        //Saathi
        MATHAM,SAMAYA_PIRIVU,SAATHI_PIRIVU,SAATHI_ULPIRIVU;
    }

}
