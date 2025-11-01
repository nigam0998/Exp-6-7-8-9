// Add a Student
public void addStudent(Student student) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.save(student);
    session.getTransaction().commit();
    session.close();
}

// Read Students
public Student getStudent(int id) {
    Session session = sessionFactory.openSession();
    Student student = session.get(Student.class, id);
    session.close();
    return student;
}

// Update Student
public void updateStudent(Student student) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.update(student);
    session.getTransaction().commit();
    session.close();
}

// Delete Student
public void deleteStudent(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Student student = session.get(Student.class, id);
    if(student != null) {
        session.delete(student);
    }
    session.getTransaction().commit();
    session.close();
}
