package vip.openpark.generator.maven.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserDO implements Serializable {
    private Long id;

    private String code;

    private String username;

    private String real_name;

    private String nick_name;

    private String password;

    private Byte gender;

    private LocalDateTime birthday;

    private String nation;

    private String country_name;

    private String id_card;

    private String address;

    private String phone;

    private String email;

    private Byte bl_enable;

    private Byte bl_delete;

    private Long version;

    private LocalDateTime gmt_create;

    private String creator;

    private Long creator_id;

    private String creator_code;

    private LocalDateTime gmt_modify;

    private String modifier;

    private Long modifier_id;

    private String modifier_code;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getBl_enable() {
        return bl_enable;
    }

    public void setBl_enable(Byte bl_enable) {
        this.bl_enable = bl_enable;
    }

    public Byte getBl_delete() {
        return bl_delete;
    }

    public void setBl_delete(Byte bl_delete) {
        this.bl_delete = bl_delete;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public LocalDateTime getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(LocalDateTime gmt_create) {
        this.gmt_create = gmt_create;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Long creator_id) {
        this.creator_id = creator_id;
    }

    public String getCreator_code() {
        return creator_code;
    }

    public void setCreator_code(String creator_code) {
        this.creator_code = creator_code;
    }

    public LocalDateTime getGmt_modify() {
        return gmt_modify;
    }

    public void setGmt_modify(LocalDateTime gmt_modify) {
        this.gmt_modify = gmt_modify;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getModifier_id() {
        return modifier_id;
    }

    public void setModifier_id(Long modifier_id) {
        this.modifier_id = modifier_id;
    }

    public String getModifier_code() {
        return modifier_code;
    }

    public void setModifier_code(String modifier_code) {
        this.modifier_code = modifier_code;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserDO other = (UserDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getReal_name() == null ? other.getReal_name() == null : this.getReal_name().equals(other.getReal_name()))
            && (this.getNick_name() == null ? other.getNick_name() == null : this.getNick_name().equals(other.getNick_name()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getNation() == null ? other.getNation() == null : this.getNation().equals(other.getNation()))
            && (this.getCountry_name() == null ? other.getCountry_name() == null : this.getCountry_name().equals(other.getCountry_name()))
            && (this.getId_card() == null ? other.getId_card() == null : this.getId_card().equals(other.getId_card()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getBl_enable() == null ? other.getBl_enable() == null : this.getBl_enable().equals(other.getBl_enable()))
            && (this.getBl_delete() == null ? other.getBl_delete() == null : this.getBl_delete().equals(other.getBl_delete()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getGmt_create() == null ? other.getGmt_create() == null : this.getGmt_create().equals(other.getGmt_create()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreator_id() == null ? other.getCreator_id() == null : this.getCreator_id().equals(other.getCreator_id()))
            && (this.getCreator_code() == null ? other.getCreator_code() == null : this.getCreator_code().equals(other.getCreator_code()))
            && (this.getGmt_modify() == null ? other.getGmt_modify() == null : this.getGmt_modify().equals(other.getGmt_modify()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getModifier_id() == null ? other.getModifier_id() == null : this.getModifier_id().equals(other.getModifier_id()))
            && (this.getModifier_code() == null ? other.getModifier_code() == null : this.getModifier_code().equals(other.getModifier_code()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getReal_name() == null) ? 0 : getReal_name().hashCode());
        result = prime * result + ((getNick_name() == null) ? 0 : getNick_name().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getNation() == null) ? 0 : getNation().hashCode());
        result = prime * result + ((getCountry_name() == null) ? 0 : getCountry_name().hashCode());
        result = prime * result + ((getId_card() == null) ? 0 : getId_card().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getBl_enable() == null) ? 0 : getBl_enable().hashCode());
        result = prime * result + ((getBl_delete() == null) ? 0 : getBl_delete().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getGmt_create() == null) ? 0 : getGmt_create().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreator_id() == null) ? 0 : getCreator_id().hashCode());
        result = prime * result + ((getCreator_code() == null) ? 0 : getCreator_code().hashCode());
        result = prime * result + ((getGmt_modify() == null) ? 0 : getGmt_modify().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getModifier_id() == null) ? 0 : getModifier_id().hashCode());
        result = prime * result + ((getModifier_code() == null) ? 0 : getModifier_code().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", username=").append(username);
        sb.append(", real_name=").append(real_name);
        sb.append(", nick_name=").append(nick_name);
        sb.append(", password=").append(password);
        sb.append(", gender=").append(gender);
        sb.append(", birthday=").append(birthday);
        sb.append(", nation=").append(nation);
        sb.append(", country_name=").append(country_name);
        sb.append(", id_card=").append(id_card);
        sb.append(", address=").append(address);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", bl_enable=").append(bl_enable);
        sb.append(", bl_delete=").append(bl_delete);
        sb.append(", version=").append(version);
        sb.append(", gmt_create=").append(gmt_create);
        sb.append(", creator=").append(creator);
        sb.append(", creator_id=").append(creator_id);
        sb.append(", creator_code=").append(creator_code);
        sb.append(", gmt_modify=").append(gmt_modify);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifier_id=").append(modifier_id);
        sb.append(", modifier_code=").append(modifier_code);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}