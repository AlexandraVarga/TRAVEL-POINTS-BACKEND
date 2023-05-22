package com.disi.travelpoints.services;

import com.disi.travelpoints.dto.TouristAttractionDto;
import com.disi.travelpoints.dto.mapper.TouristAttractionMapper;
import com.disi.travelpoints.model.TouristAttractionEntity;
import com.disi.travelpoints.repositories.TouristAttractionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TouristAttractionServiceTest {

    private static final String NAME = "name";
    private static final String LOCATION = "location";
    private static final String TEXT_DESCRIPTION = "textDescription";
    private static final Long NR_OF_VISITS = 3L;
    private static final Double ENTRY_PRICE = 4.3D;
    private static final Double DISCOUNT = 5.4;
    private static final String VISITING_DATE = "visitingDate";
    private static final String IMAGE = "image";

    private static final String SECOND = "second ";
    private static final String NAME2 = SECOND + NAME;
    private static final String LOCATION2 = SECOND + LOCATION;
    private static final String TEXT_DESCRIPTION2 = SECOND + TEXT_DESCRIPTION;
    private static final Long NR_OF_VISITS2 = 6L;
    private static final Double ENTRY_PRICE2 = 7.6D;
    private static final Double DISCOUNT2 = 8.7;
    private static final String VISITING_DATE2 = SECOND + VISITING_DATE;
    private static final String IMAGE2 = SECOND + IMAGE;

    private static final TouristAttractionEntity touristAttraction = getFirstTouristAttractionEntity();
    private static final TouristAttractionEntity touristAttraction2 = getSecondTouristAttractionEntity();
    private static final TouristAttractionDto touristAttractionDto = getFirstTouristAttractionDto();
    private static final TouristAttractionDto touristAttractionDto2 = getSecondTouristAttractionDto();

    @Mock
    private TouristAttractionMapper touristAttractionMapper;

    @Mock
    private TouristAttractionRepository touristAttractionRepository;

    @InjectMocks
    private TouristAttractionService touristAttractionService;

    @Test
    void createAttraction() {
        when(touristAttractionMapper.toDto(touristAttraction)).thenReturn(touristAttractionDto);
        when(touristAttractionMapper.toEntity(touristAttractionDto)).thenReturn(touristAttraction);
        when(touristAttractionRepository.save(any(TouristAttractionEntity.class))).then(returnsFirstArg());

        TouristAttractionDto savedTouristAttractionDto = touristAttractionService.createAttraction(touristAttractionDto);
        assertThat(savedTouristAttractionDto).isEqualTo(touristAttractionDto);
    }

    @Test
    void getAllAttractions() {
        List<TouristAttractionEntity> touristAttractionEnties = Arrays.asList(touristAttraction, touristAttraction2);
        when(touristAttractionMapper.toDto(touristAttractionEnties)).thenReturn(Arrays.asList(touristAttractionDto, touristAttractionDto2));
        when(touristAttractionRepository.findAll()).thenReturn(touristAttractionEnties);

        List<TouristAttractionDto> touristAttractionDtos = touristAttractionService.getAllAttractions();
        assertThat(touristAttractionDtos.size()).isEqualTo(2);
        assertThat(touristAttractionDtos.get(0)).isEqualTo(touristAttractionDto);
        assertThat(touristAttractionDtos.get(1)).isEqualTo(touristAttractionDto2);
    }

    @Test
    void updateAttraction() {
        when(touristAttractionMapper.toDto(touristAttraction)).thenReturn(touristAttractionDto);
        when(touristAttractionMapper.toEntity(touristAttractionDto)).thenReturn(touristAttraction);
        when(touristAttractionRepository.save(any(TouristAttractionEntity.class))).then(returnsFirstArg());

        when(touristAttractionRepository.findById(any(Integer.class))).thenReturn(Optional.of(touristAttraction));
        TouristAttractionDto updatedTouristAttractionDto = touristAttractionService.updateAttraction(touristAttractionDto, 1);

        assertThat(updatedTouristAttractionDto).isEqualTo(touristAttractionDto);
    }

    static TouristAttractionEntity getFirstTouristAttractionEntity() {
        return TouristAttractionEntity.builder()
                .name(NAME)
                .location(LOCATION)
                .textDescription(TEXT_DESCRIPTION)
                .nrOfVisits(NR_OF_VISITS)
                .entryPrice(ENTRY_PRICE)
                .discount(DISCOUNT)
                .visitingDate(VISITING_DATE)
                .image(IMAGE)
                .build();
    }

    static TouristAttractionEntity getSecondTouristAttractionEntity() {
        return TouristAttractionEntity.builder()
                .name(NAME2)
                .location(LOCATION2)
                .textDescription(TEXT_DESCRIPTION2)
                .nrOfVisits(NR_OF_VISITS2)
                .entryPrice(ENTRY_PRICE2)
                .discount(DISCOUNT2)
                .visitingDate(VISITING_DATE2)
                .image(IMAGE2)
                .build();
    }

    static TouristAttractionDto getFirstTouristAttractionDto() {
        return TouristAttractionDto.builder()
                .name(NAME)
                .location(LOCATION)
                .textDescription(TEXT_DESCRIPTION)
                .nrOfVisits(NR_OF_VISITS)
                .entryPrice(ENTRY_PRICE)
                .discount(DISCOUNT)
                .visitingDate(VISITING_DATE)
                .image(IMAGE)
                .build();
    }

    static TouristAttractionDto getSecondTouristAttractionDto() {
        return TouristAttractionDto.builder()
                .name(NAME2)
                .location(LOCATION2)
                .textDescription(TEXT_DESCRIPTION2)
                .nrOfVisits(NR_OF_VISITS2)
                .entryPrice(ENTRY_PRICE2)
                .discount(DISCOUNT2)
                .visitingDate(VISITING_DATE2)
                .image(IMAGE2)
                .build();
    }

}
