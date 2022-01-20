package org.dz2.config;

import org.dz2.entities.Address;
import org.dz2.entities.District;
import org.dz2.entities.School;
import org.dz2.repositories.AddressesRepository;
import org.dz2.repositories.DistrictsRepository;
import org.dz2.repositories.SchoolsRepository;

import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DistrictsConfig {
    public DistrictsConfig(DistrictsRepository districtsRepository,
                           AddressesRepository addressesRepository,
                           SchoolsRepository schoolsRepository) {
        if (districtsRepository.count() != 0) return;

        List<District> districts = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();
        List<School> schools = new ArrayList<>();

        String[] addressesstr = new String[]{
                "Pushkin street, Kolotushkin house", "London SW1A 0AA",
                "20 W 34th St", "Mira street, 19, Ekaterinburg",
                "8 Marta street, 13, Ekaterinburg", "Vaynera street, 4, Ekaterinburg",
                "Lenina, 33, Ekaterinburg,", "Red Square, Moscow"
        };

        int n = 0;
        for (int i = 0; i < addressesstr.length / 2; i++) {
            District district = new District();
            districts.add(district);

            for (int j = 0; j < 2; j++) {
                Address address = new Address(addressesstr[n]);
                address.setDistrict(district);
                addresses.add(address);

                n++;
                School school = new School("School №" + n, address);
                schools.add(school);
            }
        }

        districtsRepository.saveAll(districts);
        addressesRepository.saveAll(addresses);
        schoolsRepository.saveAll(schools);
    }
}
