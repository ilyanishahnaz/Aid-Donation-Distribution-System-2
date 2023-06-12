import java.util.Comparator;
import java.util.Objects;

/**
 * This AidComparator class implements a Comparator interface 
 * The comparator compares two aid objects of aidRecipient, aidDonor, and aidQuantity
 * Returns matchedResult if aidRecipient of two objects are not equal
 * Returns donorResult if aidDonor of two objects are not equal
 * Returns qtyResult if aidQuantity of two objects are not equal
 */

public class AidComparator implements Comparator<Aid>
{
    public int compare(Aid a1, Aid a2)
    {
        int matchedResult = a2.getAidRecipient().compareTo(a1.getAidRecipient());
        if (matchedResult != 0)
        {
            if (!Objects.equals(a1.getAidRecipient(), "-") && !Objects.equals(a2.getAidRecipient(), "-")){
                matchedResult = a1.getAidRecipient().compareTo(a2.getAidRecipient());
            }
            return matchedResult;
        }
        int donorResult = a1.getAidDonor().compareTo(a2.getAidDonor());
        if (donorResult != 0)
        {
            return donorResult;
        }
        int qtyResult = Integer.compare(a1.getAidQuantity(),a2.getAidQuantity());
        if (qtyResult != 0)
        {
            return qtyResult;
        }
        return a1.getAid().compareTo(a2.getAid());
    }
}