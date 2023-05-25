package com.moviereservation.domain.movie.util;

import java.util.regex.Pattern;

import com.moviereservation.domain.movie.dto.MovieRegisterDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MovieValidator implements Validator {
    public static final String NUMBER_PATTERN = "^[0-9]+$";
    public static final String ACTOR_PATTERN = "^(?:[가-힣]{2,8}|[a-zA-Z]{2,20})(?:,[가-힣]{2,8}|,[a-zA-Z]{2,20})*$";
    public static final int RUNTIME_LENGTH_UPPER_BOUND = 5;
    public static final int AGELIMIT_LENGTH_UPPER_BOUND = 2;




    @Override
    public boolean supports(Class<?> clazz) {
        return MovieRegisterDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MovieRegisterDto movie = (MovieRegisterDto) target;
        validateName(movie.getName(), errors);
        validateStory(movie.getStory(), errors);
        validateRunningTime(movie.getRunningTime(), errors);
        validateDirector(movie.getDirector(), errors);
        validateActor(movie.getActor(), errors);
        validateAgeLimit(movie.getAgeLimit(), errors);
    }

    private static void validateName(String name, Errors errors) {
        if (StringUtils.isEmpty(name)) {
            errors.rejectValue("name", "movie.name.empty");
            return;
        }
        if (name.isBlank()) {
            errors.rejectValue("name", "movie.name.empty");
        }
    }

    private static void validateStory(String story, Errors errors) {
        if (StringUtils.isEmpty(story)) {
            errors.rejectValue("story", "movie.story.empty");
            return;
        }
        if (story.isBlank()) {
            errors.rejectValue("story", "movie.story.empty");
        }
    }

    private void validateRunningTime(String runTime, Errors errors) {
        if (StringUtils.isEmpty(runTime)) {
            errors.rejectValue("runningTime", "movie.runTime.empty");
            return;
        }
        if (runTime.isBlank()) {
            errors.rejectValue("runningTime", "movie.runTime.empty");
        }
        if (!Pattern.matches(NUMBER_PATTERN, runTime)){
            errors.rejectValue("runningTime", "movie.runTime.pattern");
        }
        if(runTime.length() > RUNTIME_LENGTH_UPPER_BOUND){
            errors.rejectValue("runningTime", "movie.runTime.pattern");
        }
    }

    private void validateDirector(String director, Errors errors) {
        if (StringUtils.isEmpty(director)) {
            errors.rejectValue("director", "movie.director.empty");
            return;
        }
        if (director.isBlank()) {
            errors.rejectValue("director", "movie.director.empty");
        }
    }

    private void validateActor(String actor, Errors errors) {
        if (StringUtils.isEmpty(actor)) {
            errors.rejectValue("actor", "movie.actor.empty");
            return;
        }
        if (actor.isBlank()) {
            errors.rejectValue("actor", "movie.actor.empty");
        }

        if(!Pattern.matches(ACTOR_PATTERN, actor)){
            errors.rejectValue("actor", "movie.actor.pattern");
        }

    }

    private void validateAgeLimit(String ageLimit, Errors errors) {
        if (StringUtils.isEmpty(ageLimit)) {
            errors.rejectValue("ageLimit", "movie.ageLimit.empty");
            return;
        }
        if (ageLimit.isBlank()) {
            errors.rejectValue("ageLimit", "movie.ageLimit.empty");
        }
        if (ageLimit.length() > AGELIMIT_LENGTH_UPPER_BOUND) {
            errors.rejectValue("ageLimit", "movie.ageLimit.pattern");
            return;
        }
        if (!Pattern.matches(NUMBER_PATTERN, ageLimit)) {
            errors.rejectValue("ageLimit", "movie.ageLimit.pattern");
        }
    }
}

