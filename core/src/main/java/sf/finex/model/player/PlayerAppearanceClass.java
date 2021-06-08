package sf.finex.model.player;

import sf.l2j.gameserver.model.base.ClassId;
import sf.l2j.gameserver.model.base.ClassRace;

/**
 * @author m0nster.mind
 */
public enum PlayerAppearanceClass {

    FIGHTER {
        @Override
        public int getNetworkId(ClassRace race) {
            ClassId id;
            switch (race) {
                case HUMAN:
                    id = ClassId.HumanFighter;
                    break;
                case ELF:
                    id = ClassId.ElvenFighter;
                    break;
                case DARK_ELF:
                    id = ClassId.DarkFighter;
                    break;
                case ORC:
                    id = ClassId.OrcFighter;
                    break;
                case DWARF:
                    id = ClassId.DwarvenFighter;
                    break;
                default:
                    throw new RuntimeException("Unknown race: " + race);
            }

            return id.ordinal();
        }
    },
    WIZARD {
        @Override
        public int getNetworkId(ClassRace race) {
            ClassId id;
            switch (race) {
                case HUMAN:
                    id = ClassId.HumanWizard;
                    break;
                case ELF:
                    id = ClassId.ElvenWizard;
                    break;
                case DARK_ELF:
                    id = ClassId.DarkWizard;
                    break;
                case ORC:
                    id = ClassId.OrcMystic;
                    break;
                case DWARF:
                    id = ClassId.DwarvenFighter;
                    break;
                default:
                    throw new RuntimeException("Unknown race: " + race);
            }

            return id.ordinal();
        }
    };

    public abstract int getNetworkId(ClassRace race);

}
