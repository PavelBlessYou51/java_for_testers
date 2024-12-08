package model;

public record RecordData(String id, String firstName, String lastName, String address, String photo, String home,
                         String mobile, String work, String secondary) {

    public RecordData() {
        this("", "", "", "", "", "", "", "", "");
    }


    @Override
    public String toString() {
        return "RecordData[" +
                "id=" + id + ", " +
                "firstName=" + firstName + ", " +
                "lastName=" + lastName + ']';
    }


    public RecordData withFirstName(String firstName) {
        return new RecordData(this.id, firstName, this.lastName, this.address, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public RecordData withLastName(String lastName) {
        return new RecordData(this.id, this.firstName, lastName, this.address, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public RecordData withId(String id) {
        return new RecordData(id, this.firstName, this.lastName, this.address, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public RecordData withAddress(String id) {
        return new RecordData(this.id, this.firstName, this.lastName, address, this.photo, this.home, this.mobile, this.work, this.secondary);
    }

    public RecordData withPhoto(String photo) {
        return new RecordData(this.id, this.firstName, this.lastName, this.address, photo, this.home, this.mobile, this.work, this.secondary);
    }

    public RecordData withHomePhone(String photo) {
        return new RecordData(this.id, this.firstName, this.lastName, this.address, photo, home, this.mobile, this.work, this.secondary);
    }

    public RecordData withMobilePhone(String photo) {
        return new RecordData(this.id, this.firstName, this.lastName, this.address, photo, this.home, mobile, this.work, this.secondary);
    }

    public RecordData withWorkPhone(String photo) {
        return new RecordData(this.id, this.firstName, this.lastName, this.address, photo, this.home, this.mobile, work, this.secondary);
    }

    public RecordData withSecondaryPhone(String photo) {
        return new RecordData(this.id, this.firstName, this.lastName, this.address, photo, this.home, this.mobile, this.work, secondary);
    }
}
