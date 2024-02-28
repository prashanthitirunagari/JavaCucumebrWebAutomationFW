Feature: Getting course

  @course1
  Scenario: User is able to get a course
    Given go to preferences page
    When select audio "off"
    And select subtitles "on"
    When click Submit button
    Then verify the page loaded fully
    Then verify user landed to home page
    When click Begin button
    Then verify header is "Course Menu"
    And click right arrow for next page
    Then verify header is "Introduction"
    And click right arrow for next page
    Then verify the right arrow unlocked just after the video finished
    And click right arrow for next page
    Then verify header is "Diversity and Inclusion Drive Success"
    Then verify the right arrow locked until all readings done
    When click "Profitability"
    When close pop-up
    Then verify the right arrow locked until all readings done
    When click "Innovation"
    When close pop-up
    Then verify the right arrow locked until all readings done
    When click "Effectiveness"
    When close pop-up
    Then verify the right arrow unlocked just after readings finished
    And click right arrow for next page
    Then verify header is "The Path to Success"
    And click right arrow for next page
    Then verify header is "Inclusion"
    And click right arrow for next page
    Then verify header is "What Is Inclusion?"
    And click right arrow for next page
    Then verify the right arrow unlocked just after the video finished
    And click right arrow for next page
    Then verify header is "Inclusion in Action"
    And click down arrow
    Then verify the right arrow locked until all readings done
    And click "Seek"
    When close pop-up
    Then verify the right arrow locked until all readings done
    And click "Listen"
    When close pop-up
    Then verify the right arrow locked until all readings done
    And click "Represent"
    Then verify the right arrow unlocked just after readings finished
    When close pop-up
    Then verify the right arrow unlocked just after readings finished
    And click right arrow for next page
    Then verify header is "Diversity"
    And click right arrow for next page
    Then verify the right arrow unlocked just after the video finished
    And click right arrow for next page
    Then verify header is "We Value Diversity"
    And click right arrow for next page
    Then verify the right arrow unlocked just after the video finished
    And click right arrow for next page
    Then verify header is "Diversity in Action"
    And click "Business network"
    Then verify the right arrow locked until all readings done
    When close pop-up
    And click "Event"
    Then verify the right arrow locked until all readings done
    When close pop-up
    And click "Conversation"
    When close pop-up
    Then verify the right arrow unlocked just after readings finished
    And click right arrow for next page
    Then verify header is "Equity"
    And click right arrow for next page
    Then verify the right arrow unlocked just after the video finished
    And click right arrow for next page
    Then verify header is "What Is Equity?"
    And click right arrow for next page
    Then verify the right arrow unlocked just after the video finished
    And click right arrow for next page
    Then verify header is "Equity in Action"
    And click right arrow for next page
    Then verify header is "Conclusion"
    And click right arrow for next page
    Then verify header is "Final Thoughts"
    And click right arrow for next page
    Then verify header is "Diploma"
    And take a screenshot of diploma
