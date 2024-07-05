package com.example.puppicasso.domain.ai.prompt;

public enum DogBreed {
    BEAGLE("Beagle", "비글"),
    BOXER("Boxer", "복서"),
    BULLDOG("Bulldog", "불도그"),
    CHIHUAHUA("Chihuahua", "치와와"),
    DACHSHUND("Dachshund", "닥스훈트"),
    DALMATIAN("Dalmatian", "달마시안"),
    DOBERMAN("Doberman", "도베르만"),
    FRENCH_BULLDOG("French Bulldog", "프렌치 불도그"),
    GERMAN_SHEPHERD("German Shepherd", "저먼 셰퍼드"),
    GOLDEN_RETRIEVER("Golden Retriever", "골든 리트리버"),
    GREAT_DANE("Great Dane", "그레이트 데인"),
    HUSKY("Husky", "허스키"),
    LABRADOR_RETRIEVER("Labrador Retriever", "래브라도 리트리버"),
    MALTESE("Maltese", "말티즈"),
    MASTIFF("Mastiff", "마스티프"),
    POMERANIAN("Pomeranian", "포메라니안"),
    POODLE("Poodle", "푸들"),
    PUG("Pug", "퍼그"),
    ROTTWEILER("Rottweiler", "로트와일러"),
    SHIH_TZU("Shih Tzu", "시추"),
    SIBERIAN_HUSKY("Siberian Husky", "시베리안 허스키"),
    YORKSHIRE_TERRIER("Yorkshire Terrier", "요크셔 테리어"),
    AUSTRALIAN_SHEPHERD("Australian Shepherd", "오스트레일리언 셰퍼드"),
    BASSET_HOUND("Basset Hound", "바셋 하운드"),
    BELGIAN_MALINOIS("Belgian Malinois", "벨지안 말리노이즈"),
    BERNESE_MOUNTAIN_DOG("Bernese Mountain Dog", "버니즈 마운틴 도그"),
    BORDER_COLLIE("Border Collie", "보더 콜리"),
    BOSTON_TERRIER("Boston Terrier", "보스턴 테리어"),
    BRITTANY("Brittany", "브리타니"),
    CAVALIER_KING_CHARLES_SPANIEL("Cavalier King Charles Spaniel", "카발리에 킹 찰스 스파니엘"),
    CHESAPEAKE_BAY_RETRIEVER("Chesapeake Bay Retriever", "체서피크 베이 리트리버"),
    COCKER_SPANIEL("Cocker Spaniel", "코커 스파니엘"),
    COLLIE("Collie", "콜리"),
    ENGLISH_SETTER("English Setter", "잉글리시 세터"),
    ENGLISH_SPRINGER_SPANIEL("English Springer Spaniel", "잉글리시 스프링어 스파니엘"),
    GERMAN_SHORTHAIRED_POINTER("German Shorthaired Pointer", "저먼 쇼트헤어드 포인터"),
    GIANT_SCHNAUZER("Giant Schnauzer", "자이언트 슈나우저"),
    IRISH_SETTER("Irish Setter", "아이리시 세터"),
    ITALIAN_GREYHOUND("Italian Greyhound", "이탈리안 그레이하운드"),
    JACK_RUSSELL_TERRIER("Jack Russell Terrier", "잭 러셀 테리어"),
    LHASA_APSO("Lhasa Apso", "라사 압소"),
    MINIATURE_SCHNAUZER("Miniature Schnauzer", "미니어처 슈나우저"),
    NEWFOUNDLAND("Newfoundland", "뉴펀들랜드"),
    OLD_ENGLISH_SHEEPDOG("Old English Sheepdog", "올드 잉글리시 쉽독"),
    SAINT_BERNARD("Saint Bernard", "세인트 버나드"),
    SAMOYED("Samoyed", "사모예드"),
    SHIBA_INU("Shiba Inu", "시바 이누"),
    STAFFORDSHIRE_BULL_TERRIER("Staffordshire Bull Terrier", "스태퍼드셔 불 테리어"),
    WEIMARANER("Weimaraner", "와이마라너"),
    WEST_HIGHLAND_WHITE_TERRIER("West Highland White Terrier", "웨스트 하이랜드 화이트 테리어");

    private final String englishName;
    private final String koreanName;

    DogBreed(String englishName, String koreanName) {
        this.englishName = englishName;
        this.koreanName = koreanName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
