package by.tms.projectinterpol.service.impl;


import by.tms.projectinterpol.dao.RequestDAO;
import by.tms.projectinterpol.dao.impl.RequestDAOImpl;
import by.tms.projectinterpol.entity.Gender;
import by.tms.projectinterpol.entity.Requests;
import by.tms.projectinterpol.entity.Status;
import by.tms.projectinterpol.service.RequestService;

import java.util.List;

public class RequestServiceImpl implements RequestService {

    private static RequestServiceImpl instance;
    private final RequestDAO requestDAO;

    private RequestServiceImpl() {
        requestDAO = RequestDAOImpl.getInstance();
    }

    public static RequestServiceImpl getInstance() {
        if (instance == null) {
            instance = new RequestServiceImpl();
        }
        return instance;
    }

    @Override
    public long save(Requests requests) {
        return requestDAO.save(requests);
    }

    @Override
    public void update(Requests requests) {
        requestDAO.update(requests);
    }

    @Override
    public void delete(Requests requests) {
        requestDAO.delete(requests);
    }

    @Override
    public List<Requests> findAll() {
        return requestDAO.findAll();
    }

    @Override
    public List<Requests> findRequestByAge(Integer age) {
        return requestDAO.findRequestByAge(age);
    }

    @Override
    public List<Requests> findRequestsByGender(Gender gender) {
        return requestDAO.findRequestsByGender(gender);
    }

    @Override
    public List<Requests> findRequestsByApproval(boolean approved) {
        return requestDAO.findRequestsByApproval(approved);
    }

    @Override
    public List<Requests> findRequestByNationality(String nationality) {
        return requestDAO.findRequestByNationality(nationality);
    }

    @Override
    public List<Requests> findRequestByName(String firstName, String lastName) {
        return requestDAO.findRequestByName(firstName, lastName);
    }

    @Override
    public long findAmountRequestByStatusAndApproval(Status status, boolean approved) {
        return requestDAO.findAmountRequestByStatusAndApproval(status, approved);
    }

    @Override
    public List<Requests> findRequestsByStatusAndApprovalWithLimitAndOffset(Status status, boolean approved, String limit, String offset) {
        return requestDAO.findRequestsByStatusAndApprovalWithLimitAndOffset(status, approved, limit, offset);
    }
}
