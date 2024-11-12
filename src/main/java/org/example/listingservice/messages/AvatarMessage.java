package org.example.listingservice.messages;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvatarMessage {
    private Long buildingId;
    private byte[] file;
    private List<byte[]> files = new ArrayList<>();
}
