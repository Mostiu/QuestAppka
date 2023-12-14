package com.example.backend;

import com.example.backend.tag.Tag;
import com.example.backend.technology_tags.TechnologyTags;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class TagTests {

    @Test
    public void testTagCreation() {
        // Given
        String tagName = "Java";
        Tag tag = new Tag(tagName);

        // When
        String retrievedName = tag.getName();

        // Then
        Assertions.assertEquals(tagName, retrievedName);
    }

    @Test
    public void testAddTechnologyTags() {
        // Given
        Tag tag = new Tag("Java");
        TechnologyTags techTag1 = new TechnologyTags();
        TechnologyTags techTag2 = new TechnologyTags();

        // When
        tag.addTechnologyTags(techTag1);
        tag.addTechnologyTags(techTag2);

        // Then
        Set<TechnologyTags> techTags = tag.getTechnologyTags();
        Assertions.assertEquals(2, techTags.size());
        Assertions.assertTrue(techTags.contains(techTag1));
        Assertions.assertTrue(techTags.contains(techTag2));
    }

    @Test
    public void testRemoveTechnologyTags() {
        // Given
        Tag tag = new Tag("Java");
        TechnologyTags techTag1 = new TechnologyTags();
        TechnologyTags techTag2 = new TechnologyTags();
        tag.addTechnologyTags(techTag1);
        tag.addTechnologyTags(techTag2);

        // When
        tag.getTechnologyTags().remove(techTag1);

        // Then
        Set<TechnologyTags> techTags = tag.getTechnologyTags();
        Assertions.assertEquals(1, techTags.size());
        Assertions.assertFalse(techTags.contains(techTag1));
        Assertions.assertTrue(techTags.contains(techTag2));
    }

    @Test
    public void testTagToString() {
        // Given
        Tag tag = new Tag("Java");

        // When
        String tagString = tag.toString();

        // Then
        Assertions.assertTrue(tagString.contains("id=" + tag.getId()));
        Assertions.assertTrue(tagString.contains("name='Java'"));
        Assertions.assertTrue(tagString.contains("technologyTags=" + tag.getTechnologyTags()));
    }
}

