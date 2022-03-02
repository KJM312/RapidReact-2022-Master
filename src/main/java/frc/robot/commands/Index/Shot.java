package frc.robot.commands.Index;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Serializer;
import frc.robot.subsystems.Shooter;

public class Shot extends CommandBase {

    private Shooter shooter;
    private Index index;
    private Intake intake;
    private Serializer serializer;
    private IndexType indexType;

    public Shot(Shooter shooter, Intake intake, Index index, Serializer serializer, IndexType indexType){
        this.shooter = shooter;
        this.index = index;
        this.intake = intake;
        this.serializer = serializer; 
        this.indexType = indexType;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        switch(indexType){
            case LOADING:
                break;
            case SINGLESHOT:
                Index.setIndexState(Index.IndexState.SHOOTING);
            case DEFAULT:
              Index.setIndexState(Index.IndexState.DEFAULT);
              serializer.disable();
        }
    }

    @Override
    public void end(boolean interrupted) {
        intake.disable();
        shooter.disable();
        index.disable();
        Index.setIndexState(Index.IndexState.DEFAULT);
    }

    public enum IndexType{
        LOADING,
        SINGLESHOT,
        DEFAULT
    }
}
