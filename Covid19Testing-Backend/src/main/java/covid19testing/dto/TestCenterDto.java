package covid19testing.dto;

public class TestCenterDto {
    private String name;
    private String address;
    private String city;
    private String province;

    public TestCenterDto(){
    }

    public TestCenterDto(String name, String address, String city, String province){
        this.name = name;
        this.city = city;
        this.address = address;
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
