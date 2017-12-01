package <%=packageName%>.auth.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum UserType {
	
	TRAINEE("Trainee"), TRAINER("Trainer"), ADMIN("Admin");
    
	private String description;

    UserType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Map<String, String> getUserTypesAsMap() {
        List<UserType> candidateTypeEnumConstants = Arrays.asList(UserType.values());
        return candidateTypeEnumConstants.stream().collect(Collectors.toMap(UserType::getDescription, UserType::getDescription));
    }
	

}
