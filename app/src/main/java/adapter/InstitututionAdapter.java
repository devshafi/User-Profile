package adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.List;

import learn.shafi.nestedrecyclerview.R;
import pojo.Institution;

/**
 * Created by frshafi on 1/31/18.
 */

public class InstitututionAdapter extends RecyclerView.Adapter<InstitututionAdapter.ViewHolder> {


    private List<Institution> institutionList;
    private Context context;

    public InstitututionAdapter(Context context, List<Institution> institutions){
        this.context = context;
        this.institutionList = institutions;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return context;
    }


    // connecting viewHolder

    @Override
    public InstitututionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // inflate the custom layout
        View InstitutionView = inflater.inflate(R.layout.single_institute_row,parent,false);

        return new ViewHolder(InstitutionView);

    }

    // setting value from Institution list

    @Override
    public void onBindViewHolder(final InstitututionAdapter.ViewHolder holder, final int position) {

        final Institution institution = institutionList.get(position);

        holder.nameInstTV.setText(institution.getInstituteName());
        holder.fieldOfStudyTV.setText(institution.getFieldOfStudy());
        holder.degreeTV.setText(institution.getDegree());
        holder.gradeTV.setText(institution.getGrade());
        holder.passYearTV.setText(institution.getYear());
        holder.iconEditInstIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();

                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
                LayoutInflater inflater = LayoutInflater.from(context);
                View bottomSheetView = inflater.inflate(R.layout.instituition_edit_bottom_dialog,null);
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.setCancelable(false);
                bottomSheetDialog.show();

                final EditText institutionET = bottomSheetView.findViewById(R.id.institutionET);
                final EditText degreeET = bottomSheetView.findViewById(R.id.degreeET);
                final EditText fieldOfStudyET = bottomSheetView.findViewById(R.id.fieldOfStudyET);
                final EditText gradeET = bottomSheetView.findViewById(R.id.gradeET);
                final EditText fromET = bottomSheetView.findViewById(R.id.fromET);
                final EditText toET = bottomSheetView.findViewById(R.id.toET);
                ImageView doneIV = bottomSheetView.findViewById(R.id.doneIV);


                institutionET.setText(holder.nameInstTV.getText());
                fieldOfStudyET.setText(holder.fieldOfStudyTV.getText());
                degreeET.setText(holder.degreeTV.getText());
                gradeET.setText(holder.gradeTV.getText());


                String durationOfStudy[] = holder.passYearTV.getText().toString().split("-");

                fromET.setText(durationOfStudy[0]);
                fromET.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AppCompatDialog dialog;
                        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
                        LayoutInflater inflater = LayoutInflater.from(context);
                        View timePickerView = inflater.inflate(R.layout.time_picker,null);
                        builder.setView(timePickerView);

                        final NumberPicker studyDurationPicker = timePickerView.findViewById(R.id.studyDurationNP);
                        studyDurationPicker.setMinValue(1970);
                        studyDurationPicker.setMaxValue(2040);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                Log.d("FROM",String.valueOf(studyDurationPicker.getValue()));
                                fromET.setText(String.valueOf(studyDurationPicker.getValue()));

                            }
                        });

                        dialog = builder.create();
                        dialog.show();


                    }
                });
                toET.setText(durationOfStudy[1]);
                toET.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AppCompatDialog dialog;
                        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
                        LayoutInflater inflater = LayoutInflater.from(context);
                        View timePickerView = inflater.inflate(R.layout.time_picker,null);
                        builder.setView(timePickerView);

                        final NumberPicker studyDurationPicker = timePickerView.findViewById(R.id.studyDurationNP);
                        studyDurationPicker.setMinValue(1970);
                        studyDurationPicker.setMaxValue(2040);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Log.d("TO",String.valueOf(studyDurationPicker.getValue()));
                                toET.setText(String.valueOf(studyDurationPicker.getValue()));
                            }
                        });
                        dialog = builder.create();
                        dialog.show();
                    }
                });

                doneIV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        holder.nameInstTV.setText(institutionET.getText().toString());
                        holder.fieldOfStudyTV.setText(fieldOfStudyET.getText().toString());
                        holder.degreeTV.setText(degreeET.getText().toString());
                        holder.gradeTV.setText(gradeET.getText().toString());
                        String studyDuration = fromET.getText().toString()+"-"+toET.getText().toString();
                        holder.fieldOfStudyTV.setText(studyDuration);
                        bottomSheetDialog.hide();
                    }
                });

            }

        });


    }

    @Override
    public int getItemCount() {
        return institutionList.size();
    }


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView nameInstTV;
        TextView fieldOfStudyTV;
        TextView degreeTV;
        TextView gradeTV;
        TextView passYearTV;
        ImageView iconEditInstIV;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            nameInstTV = itemView.findViewById(R.id.nameInstTV);
            fieldOfStudyTV = itemView.findViewById(R.id.fieldOfStudyTV);
            degreeTV = itemView.findViewById(R.id.degreeTV);
            gradeTV = itemView.findViewById(R.id.gradeTV);
            passYearTV = itemView.findViewById(R.id.passYearTV);
            iconEditInstIV =  itemView.findViewById(R.id.iconEditInstIV);

        }
    }
}
