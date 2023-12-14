package com.example.backend;

import com.example.backend.tag.Tag;
import com.example.backend.technology.Technology;
import com.example.backend.technology_tags.TechnologyTags;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TechnologyTagsTests {

    @Mock
    private Technology technology;

    @Mock
    private Tag tag;

    @InjectMocks
    private TechnologyTags technologyTags;

    @Test
    void testGettersAndSetters() {
        // Set up
        technologyTags.setId(1L);
        technologyTags.setTechnology(technology);
        technologyTags.setTag(tag);

        // Verify
        assertEquals(1L, technologyTags.getId());
        assertEquals(technology, technologyTags.getTechnology());
        assertEquals(tag, technologyTags.getTag());
    }

    @Test
    void testToString() {
        // Set up
        technologyTags.setId(1L);
        technologyTags.setTechnology(technology);
        technologyTags.setTag(tag);

        // Verify
        assertEquals("TechnologyTags{id=1, technology=" + technology + ", tag=" + tag + '}', technologyTags.toString());
    }
}

