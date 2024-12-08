package manager;

import model.GroupData;
import model.RecordData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordHelper extends HelperBase {

    public RecordHelper(ApplicationManager manager) {
        super(manager);
    }


    public void addNewRecord(RecordData record) {
        initRecordCreation();
        fillRecordForm(record);
        submitRecordCreation();
        returnToHomePage();
    }

    public void createNewRecord(RecordData record, GroupData group) {
        initRecordCreation();
        fillRecordForm(record);
        selectGroup(group);
        submitRecordCreation();
        returnToHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());

    }

    public void removeRecord(RecordData record) {
        selectRecord(record);
        submitRecordRemove();
    }

    public void removeAllRecord() {
        click(By.id("MassCB"));
        submitRecordRemove();
    }

    public int getRecordCounts() {
        List<WebElement> inputs = manager.driver.findElements(By.cssSelector("input[name='selected[]']"));
        return inputs.size();
    }

    public void modifyRecord(RecordData record, RecordData modifiedRecord) {
        selectRecord(record);
        initRecordModification(record);
        fillRecordForm(modifiedRecord);
        submitGroupModification();
        returnToHomePage();
    }


    public ArrayList<RecordData> getList() {
        ArrayList<RecordData> records = new ArrayList<>();
        List<WebElement> strings = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement string : strings) {
            String id = string.findElement(By.name("selected[]")).getAttribute("value");
            String lastName = string.findElement(By.xpath("//tr[@name='entry']/td[2] ")).getText();
            String firstName = string.findElement(By.xpath("//tr[@name='entry']/td[3] ")).getText();
            records.add(new RecordData().withId(id).withLastName(lastName).withFirstName(firstName));
        }
        return records;
    }

    public void returnToHomePage() {
        click(By.xpath("//a[contains(text(), 'home')]"));
    }

    private void submitRecordRemove() {
        click(By.cssSelector("input[value='Delete']"));
    }

    private void submitGroupModification() {
        click(By.cssSelector("input[value='Update']"));
    }


    private void initRecordModification(RecordData record) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s'", record.id())));
    }


    private void selectRecord(RecordData record) {
        click(By.cssSelector(String.format("input[id='%s']", record.id())));
    }

    private void initRecordCreation() {
        click(By.linkText("add new"));
    }

    private void fillRecordForm(RecordData record) {
        type(By.name("firstname"), record.firstName());
        type(By.name("lastname"), record.lastName());
        attach(By.name("photo"), record.photo());
    }

    private void submitRecordCreation() {
        click(By.name("submit"));
    }


    public String getPhones(RecordData contact) {
        return manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }
}

