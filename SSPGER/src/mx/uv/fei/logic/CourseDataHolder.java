package mx.uv.fei.logic;


/**
 *
 * @author sue
 */
public class CourseDataHolder {
    private static CourseDataHolder course;
    private String textFieldNrcValue;
    private EducationalExperience choiceBoxEducationalExperienceValue;
    private SchoolPeriod choiceBoxSchoolPeriodValue;
    private Section choiceBoxSectionValue;
    private Block choiceBoxBlockValue;
    private User choiceBoxProfessorValue;
    
    private CourseDataHolder() {
    }

    public static void setCourse(CourseDataHolder course) {
        CourseDataHolder.course = course;
    }

    public void setTextFieldNrcValue(String textFieldNrcValue) {
        this.textFieldNrcValue = textFieldNrcValue;
    }

    public void setChoiceBoxEducationalExperienceValue(EducationalExperience choiceBoxEducationalExperienceValue) {
        this.choiceBoxEducationalExperienceValue = choiceBoxEducationalExperienceValue;
    }

    public void setChoiceBoxSchoolPeriodValue(SchoolPeriod choiceBoxSchoolPeriodValue) {
        this.choiceBoxSchoolPeriodValue = choiceBoxSchoolPeriodValue;
    }

    public void setChoiceBoxSectionValue(Section choiceBoxSectionValue) {
        this.choiceBoxSectionValue = choiceBoxSectionValue;
    }

    public void setChoiceBoxBlockValue(Block choiceBoxBlockValue) {
        this.choiceBoxBlockValue = choiceBoxBlockValue;
    }

    public void setChoiceBoxProfessorValue(User choiceBoxProfessorValue) {
        this.choiceBoxProfessorValue = choiceBoxProfessorValue;
    }

    public String getTextFieldNrcValue() {
        return textFieldNrcValue;
    }

    public EducationalExperience getChoiceBoxEducationalExperienceValue() {
        return choiceBoxEducationalExperienceValue;
    }

    public SchoolPeriod getChoiceBoxSchoolPeriodValue() {
        return choiceBoxSchoolPeriodValue;
    }

    public Section getChoiceBoxSectionValue() {
        return choiceBoxSectionValue;
    }

    public Block getChoiceBoxBlockValue() {
        return choiceBoxBlockValue;
    }

    public User getChoiceBoxProfessorValue() {
        return choiceBoxProfessorValue;
    }
    
    public void destroyCourseDataHolder() {
        this.textFieldNrcValue = null;
        this.choiceBoxEducationalExperienceValue = null;
        this.choiceBoxSchoolPeriodValue = null;
        this.choiceBoxSectionValue = null;
        this.choiceBoxBlockValue = null;
        this.choiceBoxProfessorValue = null;
    }
    
    public static CourseDataHolder getCourseDataHolder() {
        if (course == null) {
            course = new CourseDataHolder();
        }
        return course;    
    }
}
