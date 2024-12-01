package model;

public record RecordData(String id, String firstName, String lastName, String address, String photo) {

    public RecordData() {
        this("", "", "", "", "");
    }


    @Override
    public String toString() {
        return "RecordData[" +
                "id=" + id + ", " +
                "firstName=" + firstName + ", " +
                "lastName=" + lastName + ']';
    }


    public RecordData withFirstName(String firstName) {
        return new RecordData(this.id, firstName, this.lastName, this.address, this.photo);
    }

    public RecordData withLastName(String lastName) {
        return new RecordData(this.id, this.firstName, lastName, this.address, this.photo);
    }

    public RecordData withId(String id) {
        return new RecordData(id, this.firstName, this.lastName, this.address, this.photo);
    }

    public RecordData withAddress(String id) {
        return new RecordData(this.id, this.firstName, this.lastName, address, this.photo);
    }

    public RecordData withPhoto(String photo) {
        return new RecordData(this.id, this.firstName, this.lastName, this.address, photo);
    }
}
