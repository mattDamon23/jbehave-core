/*
 * Created on 27-Aug-2004
 * 
 * (c) 2003-2004 ThoughtWorks Ltd
 *
 * See license.txt for license details
 */
package com.thoughtworks.jbehave.extensions.story.renderers;

import java.io.PrintStream;

import com.thoughtworks.jbehave.extensions.story.domain.Context;
import com.thoughtworks.jbehave.extensions.story.domain.Event;
import com.thoughtworks.jbehave.extensions.story.domain.Expectation;
import com.thoughtworks.jbehave.extensions.story.domain.Given;
import com.thoughtworks.jbehave.extensions.story.domain.GivenScenario;
import com.thoughtworks.jbehave.extensions.story.domain.Outcome;
import com.thoughtworks.jbehave.extensions.story.domain.Scenario;
import com.thoughtworks.jbehave.extensions.story.domain.Story;
import com.thoughtworks.jbehave.extensions.story.visitor.Visitor;
import com.thoughtworks.jbehave.util.CaseConverter;


/**
 * @author <a href="mailto:dan.north@thoughtworks.com">Dan North</a>
 */
public class PlainTextRenderer implements Visitor {

    private final PrintStream out;
    private String nextWord;

    public PlainTextRenderer(PrintStream out) {
        this.out = out;
    }

    public void visitStory(Story story) {
        out.println("Story: " + story.getName());
        out.println();
        out.println("As a " + story.getRole());
        out.println("I want " + story.getFeature());
        out.println("So that " + story.getBenefit());
    }

    public void visitScenario(Scenario scenario) {
        out.println();
        out.println("Scenario: " + scenario.getDescription());
        out.println();
    }
    
    public void visitContext(Context context) {
        nextWord = "Given ";
    }

    public void visitGiven(Given given) {
        out.print(nextWord);
        if (given instanceof GivenScenario) {
            GivenScenario givenScenario = (GivenScenario) given;
            out.print("\"" + new CaseConverter().toSeparateWords(givenScenario.getScenario().getDescription()) + "\"");
            out.print(" from ");
            out.println("\"" + new CaseConverter().toSeparateWords(givenScenario.getStory()) + "\"");
        }
        else {
            out.println(new CaseConverter().toSeparateWords(given));
        }
        nextWord = "and ";
    }

    public void visitEvent(Event event) {
        out.println("When " + new CaseConverter().toSeparateWords(event));
    }

    public void visitOutcome(Outcome outcome) {
        nextWord = "Then ";
    }
    
    public void visitExpectation(Expectation expectation) {
        out.println(nextWord + new CaseConverter().toSeparateWords(expectation));
        nextWord = "and ";
    }
}
