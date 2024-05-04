package spring.learn.spring.response;

import java.io.Serializable;
import java.util.List;

public class AutoCompletionWrapperDTO implements Serializable {
    private List<AutoCompletionDTO> autoCompletionDTOs;

    public List<AutoCompletionDTO> getAutoCompletionDTOs() {
        return autoCompletionDTOs;
    }

    public void setAutoCompletionDTOs(List<AutoCompletionDTO> autoCompletionDTOs) {
        this.autoCompletionDTOs = autoCompletionDTOs;
    }

    public AutoCompletionWrapperDTO() {
    }

    public AutoCompletionWrapperDTO(List<AutoCompletionDTO> autoCompletionDTOs) {
        this.autoCompletionDTOs = autoCompletionDTOs;
    }

}
