package com.java.hms.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.java.hms.model.DoctorDetails;
import com.java.hms.model.Specialization;
import com.java.hms.model.Status;
import com.java.hms.util.SessionHelper;

public class HospitalDaoImpl implements HospitalDao {

    SessionFactory sf;
    Session session;

    @Override
    public List<DoctorDetails> showDoctorDao() {
        sf = SessionHelper.getConnection();
        session = sf.openSession();
        Query query = session.createQuery("FROM DoctorDetails");
        List<DoctorDetails> doctorList = query.list();
        session.close();
        return doctorList;
    }

    @Override
    public List<DoctorDetails> findBySpecialization(String category) {
        sf = SessionHelper.getConnection();
        session = sf.openSession();
        Specialization specialization = Specialization.valueOf(category);
        Query query = session.createQuery("FROM DoctorDetails WHERE specialization = :category");
        query.setParameter("category", specialization);
        List<DoctorDetails> doctorList = query.list();
        session.close();
        return doctorList;
    }

    @Override
    public DoctorDetails findById(String docId) {
        sf = SessionHelper.getConnection();
        session = sf.openSession();
        Query query = session.createQuery("FROM DoctorDetails WHERE doctorId = :docId");
        query.setParameter("docId", docId);
        List<DoctorDetails> result = query.list();
        session.close();
        if (result != null && !result.isEmpty()) {
            return result.get(0);
        } else {
            return null;
        }
    }

    @Override
    public String addDoctor(DoctorDetails doctor) {
        sf = SessionHelper.getConnection();
        session = sf.openSession();
        String generatedID = generateDoctorID(session);
        doctor.setDoctorId(generatedID);
        doctor.setStatus(Status.ACTIVE);
        Transaction tx = session.beginTransaction();
        session.save(doctor);
        tx.commit();
        session.close();
        return "Added Successfully";
    }

    private String generateDoctorID(Session session) {
        Query query = session.createQuery("select count(d) from DoctorDetails d");
        long count = (long) query.uniqueResult();

        String prefix = "HSK";
        String uniquePart = String.format("DS%02d", count + 1);

        return prefix + uniquePart;
    }
}
