package com.headly.Headly;

import com.headly.Headly.dto.ApplicationOverviewDto;
import com.headly.Headly.models.ApplicationModel;
import com.headly.Headly.services.ApplicationModelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class AdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ApplicationModelService applicationModelService;

    @Test
    @DirtiesContext
    public void addApplicationToOverview() throws Exception {
        ApplicationOverviewDto first = ApplicationOverviewDto.builder().applicationid("Test1").jobid("1").userid("1").build();
        ApplicationOverviewDto second = ApplicationOverviewDto.builder().applicationid("Test2").jobid("2").userid("2").build();


        when(applicationModelService.loadApplicationModelListForApModelDto()).thenReturn(Arrays.asList(first,second));


        mockMvc.perform(get("/applicationoverview"))
                .andExpect(status().isOk())
                .andExpect(view().name("applicationoverview"))
                .andExpect(model().attribute("applications",hasSize(2)));

        verify(applicationModelService,times(1)).loadApplicationModelListForApModelDto();
    }




}
