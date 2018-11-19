package br.com.chat.profile;


import br.com.chat.profile.model.Status;
import br.com.chat.profile.model.Views;
import app.rodada.simpler.dto.Request;
import app.rodada.simpler.utils.JsonUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

public class Handler implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) {

        Request<Status> request = null;

        try {
            request = JsonUtils.mapToRequest(input, Optional.of(Status.class));
        } catch (IOException | ClassNotFoundException e) {
            //logar no cloudwatch

        }

        Service service = new Service();

        if (request.getContext().getHttpMethod().equals("GET")) {
            String profile_id = request.getQueryStringValue("profile_id");
            String last = request.getQueryStringValue("last", "1");
            List<Status> list = service.dao.getStatusByProfileId(profile_id, Integer.parseInt(last));

            JsonUtils.writeReturn(output, list, Views.ViewStatusProfile.class);
        } else if (request.getContext().getHttpMethod().equals("POST")) {
            Status status = request.getBodyFormation();
            service.dao.add(status.getProfileId(), status);

            JsonUtils.writeReturn(output, request.getBodyFormation(), Views.ViewStatusProfile.class);
        }

    }

}
