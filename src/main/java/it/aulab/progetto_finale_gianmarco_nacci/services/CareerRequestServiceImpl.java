package it.aulab.progetto_finale_gianmarco_nacci.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.aulab.progetto_finale_gianmarco_nacci.models.CareerRequest;
import it.aulab.progetto_finale_gianmarco_nacci.models.Role;
import it.aulab.progetto_finale_gianmarco_nacci.models.User;
import it.aulab.progetto_finale_gianmarco_nacci.repositories.CareerRequestRepository;
import it.aulab.progetto_finale_gianmarco_nacci.repositories.RoleRepository;
import it.aulab.progetto_finale_gianmarco_nacci.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class CareerRequestServiceImpl implements CareerRequestService {

    @Autowired
    private CareerRequestRepository careerRequestRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public boolean isRoleAlreadyAssigned(User user, CareerRequest careerRequest) {
        List<Long> allUserIds = careerRequestRepository.findAllUserIds();

        if (!allUserIds.contains(user.getId())) {
            return false;
        }

        List<Long> requests = careerRequestRepository.findByUserId(user.getId());

        return requests.stream().anyMatch(roleId -> roleId.equals(careerRequest.getRole().getId()));

    }

    @Override
    public void save(CareerRequest careerRequest, User user) {
        careerRequest.setUser(user);
        careerRequest.setIsChecked(false);
        careerRequestRepository.save(careerRequest);

        emailService.sendSimpleEmail("adminAulabpost@admin.com", "Richiesta per ruolo: " + careerRequest.getRole().getName(), "C'è una nuova richiesta di collaborazione da parte di " + user.getUsername());
    }

    @Override
    public void careerAccept(Long requestId) {
        
        CareerRequest request = careerRequestRepository.findById(requestId).get();

        User user = request.getUser();
        Role role = request.getRole();

        List<Role> rolesUser = user.getRoles();
        Role newRole = roleRepository.findByName(role.getName());
        rolesUser.add(newRole);

        user.setRoles(rolesUser);
        userRepository.save(user);
        request.setIsChecked(true);
        careerRequestRepository.save(request);

        emailService.sendSimpleEmail( user.getEmail(), "Ruolo abilitato", "Ciao la tua richiesta di collaborazione è stata accettata dalla nostra amministrazione");
        
    }

    @Override
    public CareerRequest find(Long id) {
        return careerRequestRepository.findById(id).get();
    }

}
