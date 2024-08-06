package de.monir.example.emails.mapper;

import de.monir.example.emails.config.MapstructConfig;
import de.monir.example.emails.dto.EmailUpdateDTO;
import de.monir.example.emails.model.Email;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(config = MapstructConfig.class)
public interface EmailMapper {

    @Mapping(target = "id" , ignore = true)
    @Mappings({
            @Mapping(target = "from", source = "from"),
            @Mapping(target = "emailTo", source = "emailTo"),
            @Mapping(target = "emailCC", source = "emailCC"),
            @Mapping(target = "subject", source = "subject"),
            @Mapping(target = "body", source = "body"),
            @Mapping(target = "state", source = "state")
    })
    @BeanMapping(ignoreByDefault = true)
    Email emailUpdateDTOToEmail(EmailUpdateDTO emailUpdateDTO);
}
