package com.bicasteam.movigestion.platform.profilles.interfaces.acl;

import com.bicasteam.movigestion.platform.profilles.domain.model.commands.CreateProfileCommand;
import com.bicasteam.movigestion.platform.profilles.domain.model.queries.GetProfileByEmailQuery;
import com.bicasteam.movigestion.platform.profilles.domain.model.valueobjects.EmailAddress;
import com.bicasteam.movigestion.platform.profilles.domain.services.ProfileCommandService;
import com.bicasteam.movigestion.platform.profilles.domain.services.ProfileQueryService;
import org.springframework.stereotype.Service;

@Service
public class ProfilesContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfilesContextFacade(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    public Long createProfile(String firstName, String lastName, String email, String street, String number, String city) {
        var createProfileCommand = new CreateProfileCommand(firstName, lastName, email, street, number, city);
        return profileCommandService.handle(createProfileCommand);
    }

    public Long getProfileIdByEmail(String email) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(new EmailAddress(email));
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        if (profile.isEmpty()) {
            return 0L;
        }
        return profile.get().getId();
    }
}
