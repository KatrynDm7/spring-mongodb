package ru.habrahabr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.habrahabr.dao.ContactDao;
import ru.habrahabr.dao.SequenceDao;
import ru.habrahabr.model.Contact;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ContactService {
    @Autowired private SequenceDao sequenceDao;
    @Autowired private ContactDao contactDao;

    protected String format = "MMM dd, yyyy";

    public void add(Contact contact) {
        contact.setId(sequenceDao.getNextSequenceId(Contact.COLLECTION_NAME));
        contact.setDate(this.getFormatedDate());
        contactDao.save(contact);
    }

    public void update(Contact contact) {
        contactDao.save(contact);
    }

    public Contact get(int id) {
        return contactDao.get(id);
    }

    public List<Contact> getAll() {
        return contactDao.getAll();
    }

    public void remove(int id) {
        contactDao.remove(id);
    }

    protected String getFormatedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(this.format);
        return dateFormat.format(new Date());
    }
}
