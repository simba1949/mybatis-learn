package top.simba1949.common;

/**
 * @author v_jiayytian@tencent.com
 * @date 2018/9/6 16:20
 */
public class CountryDto {

    public static String SIMBA = "SIMBA";

    public static String getString(){
        return "SIMBA1949";
    }

    private Integer id;
    private String countryName;
    private String countryCode;

    @Override
    public String toString() {
        return "CountryDto{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
