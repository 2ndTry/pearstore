package com.amiroshnikov.PearStore.service;

import com.amiroshnikov.PearStore.dto.ResponseDto;
import com.amiroshnikov.PearStore.dto.user.SighInDto;
import com.amiroshnikov.PearStore.dto.user.SighInResponseDto;
import com.amiroshnikov.PearStore.dto.user.SighupDto;
import com.amiroshnikov.PearStore.exception.AuthenticationException;
import com.amiroshnikov.PearStore.exception.CustomException;
import com.amiroshnikov.PearStore.model.AuthenticationToken;
import com.amiroshnikov.PearStore.model.User;
import com.amiroshnikov.PearStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationService authenticationService;

    @Transactional
    public ResponseDto sighup(SighupDto sighupDto) {
        if (Objects.nonNull(userRepository.findByEmail(sighupDto.getEmail()))) {
            throw new CustomException("User already present");
        }

        String encryptedPassword = sighupDto.getPassword();

        try {
            encryptedPassword = hashPassword(sighupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        User user = new User(sighupDto.getFirstName(),
                                sighupDto.getLastName(),
                                    sighupDto.getEmail(),
                                        encryptedPassword);
        userRepository.save(user);

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);

        ResponseDto responseDto = new ResponseDto("success", "User create!");
        return responseDto;
    }

    public SighInResponseDto sighIn(SighInDto sighInDto) {
        User user = userRepository.findByEmail(sighInDto.getEmail());

        if(Objects.isNull(user)) {
            throw new AuthenticationException("User is not valid");
        }

        try {
            if(!user.getPassword().equals(hashPassword(sighInDto.getPassword()))) {
                throw new AuthenticationException("Wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        AuthenticationToken token = authenticationService.getToken(user);
        if (Objects.isNull(token)) {
            throw new CustomException("Token is not present");
        }

        return new SighInResponseDto("success", token.getToken());
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();

        return hash;
    }
}
