package fb.fandroid.adv.joindbviewmodelapp.model.converter;

import android.support.annotation.Nullable;

import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import fb.fandroid.adv.joindbviewmodelapp.model.Data;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by marat.taychinov
 */

public class DataConverterFactory extends Converter.Factory {

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Type envelopedType = TypeToken.getParameterized(Data.class, type).getType();

        final Converter<ResponseBody, Data> delegate = retrofit.nextResponseBodyConverter(this, envelopedType, annotations);

        return body -> {
            Data<?> data = delegate.convert(body);
            return data.response;
        };
    }
}
