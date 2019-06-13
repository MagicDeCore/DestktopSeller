package app.services;

import app.entity.external.data.auth.domain.Company;
import app.entity.external.data.auth.domain.User;
import app.entity.external.data.auth.repository.CompanyRepository;
import app.entity.external.data.auth.repository.UserRepository;
import app.entity.local.data.auth.domain.Person;
import app.entity.local.data.auth.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired private PersonRepository personRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private CompanyRepository companyRepository;


    public String getLastLoggetCompanyName() {
        String companyName = "";
        Person person = personRepository.findByLast(true);
        if (person != null) {
            companyName = person.getCompany();
        }
        return companyName;
    }

    public boolean loginUser(String name, String companyName, String password) {
        Person person = personRepository.findByName(name);
        if (person != null) {
            if (isVerifiedLocalUser(person, companyName, password)) {
                return true;
            }
        } else {
            User user = userRepository.findByName(name);
            if (user != null) {
                System.out.println("External USER FOUND: " + user.getName());
                Company userCompany = companyRepository.findByUserId(user.getId());
                if (userCompany != null) {
                    if (verifyExternalUser(user, userCompany, companyName, password)) {
                        Person personToSave = adoptExternalUser(userCompany, user);
                        if (personToSave != null) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isVerifiedLocalUser(Person person, String companyName, String password) {
        boolean isOK = false;
        if (person.getCompany().equals(companyName)){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            if (encoder.matches(password, person.getPassword())) {
                isOK = true;
            }
        }
        return isOK;
    }

    private boolean verifyExternalUser(User user, Company userCompany, String companyName, String password) {
        System.out.println("Starting external user verification");
        boolean isOK = false;
        if (userCompany.getName().equals(companyName)) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            if (encoder.matches(password, user.getPassword())) {
                isOK = true;
            }
        }
        return isOK;
    }

    private Person adoptExternalUser(Company company, User user) {
        Person person = new Person();
        person.setCompany(company.getName());
        person.setName(user.getName());
        person.setPassword(user.getPassword());
        return personRepository.save(person);
    }


    public void initPersonDatabase() {
        Person person = personRepository.findByName("magicdecore");
        if (person != null) {
            System.out.println("you are OK, continue");
            return;
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            String token = encoder.encode("admin");

            Person admin = new Person();
            admin.setCompany("admin");
            admin.setName("admin");
            admin.setPassword(token);
            admin.setLast(true);
            personRepository.save(admin);

            Person me = new Person();
            String token2 = encoder.encode("1q2w3e!Q@W#Er");
            me.setName("magicdecore");
            me.setCompany("uoo");
            me.setPassword(token2);
            me.setLast(false);
            personRepository.save(me);
        }
    }

}
