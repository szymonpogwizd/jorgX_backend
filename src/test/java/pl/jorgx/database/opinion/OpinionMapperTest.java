package pl.jorgx.database.opinion;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.jorgX.database.opinion.*;
import pl.jorgx.database.configuration.MapperConfiguration;
import pl.jorgx.database.opinion.factory.OpinionDAOFactory;
import pl.jorgx.database.opinion.factory.OpinionDTOFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MapperConfiguration.class)
public class OpinionMapperTest {

    @Autowired
    private OpinionMapper opinionMapper;

    @Test
    void opinionDAOToOpinionInfoDto() {
        OpinionDAO opinionDAO = OpinionDAOFactory.defaultBuilder().build();

        OpinionInfoDTO opinionInfoDTO = opinionMapper.opinionDAOToOpinionInfoDto(opinionDAO);

        assertNotNull(opinionInfoDTO);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(opinionDAO.getOpinion()).isEqualTo(opinionInfoDTO.getOpinion());
        softAssertions.assertThat(opinionDAO.getOpinionType()).isEqualTo(opinionInfoDTO.getOpinionType());
        softAssertions.assertThat(opinionDAO.getUser()).isEqualTo(opinionInfoDTO.getUser());
        softAssertions.assertThat(opinionDAO.getPlace()).isEqualTo(opinionInfoDTO.getPlace());
        softAssertions.assertThat(opinionDAO.getId()).isEqualTo(opinionInfoDTO.getId());
    }

    @Test
    void opinionCreateDtoToOpinionDAO() {
        OpinionCreateDTO opinionCreateDTO = OpinionDTOFactory.defaultOpinionCreateDTO();

        OpinionDAO opinionDAO = opinionMapper.opinionCreateDtoToOpinionDAO(opinionCreateDTO);

        assertNotNull(opinionDAO);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(opinionDAO.getOpinion()).isEqualTo(opinionCreateDTO.getOpinion());
        softAssertions.assertThat("test@example.com" == opinionCreateDTO.getEmail());
        softAssertions.assertThat(opinionDAO.getPlace()).isEqualTo(opinionCreateDTO.getPlaceId());
    }

    @Test
    void opinionUpdateDtoToOpinionDAO() {
        OpinionUpdateDTO opinionUpdateDTO = OpinionDTOFactory.defaultOpinionUpdateDTO();

        OpinionDAO opinionDAO = opinionMapper.opinionUpdateDtoToOpinionDAO(opinionUpdateDTO);

        assertNotNull(opinionDAO);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(opinionDAO.getOpinion()).isEqualTo(opinionUpdateDTO.getOpinion());
        softAssertions.assertThat(null == opinionUpdateDTO.getUserId());
        softAssertions.assertThat(opinionDAO.getPlace()).isEqualTo(opinionUpdateDTO.getPlaceId());
    }
}
