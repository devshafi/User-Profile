package pojo;

import android.support.annotation.NonNull;
import android.widget.TextView;

/**
 * pojo class for Institution
 */

public class Institution implements Comparable {

    private String instituteName;
    private String fieldOfStudy;
    private String degree;
    private String grade;
    private String year;

    public Institution(String instituteName, String fieldOfStudy, String degree, String grade, String year) {
        this.instituteName = instituteName;
        this.fieldOfStudy = fieldOfStudy;
        this.degree = degree;
        this.grade = grade;
        this.year = year;
    }

    public Institution(){

    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public int compareTo(@NonNull Object obj) {

        Institution institution1 = (Institution) obj;

        String studyYear1 = institution1.getYear();
        String studyYear2 = this.getYear();

        String sy1Arr1[] = studyYear1.split("-");
        String sy1Arr2[] = studyYear2.split("-");

        int sy1Int1 = Integer.parseInt(sy1Arr1[1]);
        int sy1Int2 = Integer.parseInt(sy1Arr2[1]);

        if(sy1Int1<sy1Int2) return 1;
        if(sy1Int1>sy1Int2) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return this.getYear()+" ";
    }
}
