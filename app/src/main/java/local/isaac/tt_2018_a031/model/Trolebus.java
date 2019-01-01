package local.isaac.tt_2018_a031.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ForeignKey;
import android.os.Parcel;
import android.os.Parcelable;

import static android.arch.persistence.room.ForeignKey.SET_NULL;


@Entity(tableName = "TROLEBUS",
        foreignKeys = @ForeignKey(entity = Modelo.class,
                        parentColumns = "id_modelo",
                        childColumns = "id_modelo",
                        onDelete = SET_NULL))
public class Trolebus implements Parcelable{

    @PrimaryKey
    @ColumnInfo(name = "id_trolebus")
    private Integer idTrolebus;

    @ColumnInfo(name = "placa")
    private String placa;

    @ColumnInfo(name = "codigo_qr")
    private String codigoQR;

    @ColumnInfo(name = "id_modelo")
    private Integer idModelo;

    public Trolebus(Integer idTrolebus, String placa, String codigoQR, Integer idModel) {
        this.idTrolebus = idTrolebus;
        this.placa = placa;
        this.codigoQR = codigoQR;
        this.idModelo = idModelo;
    }

    public Trolebus() {
    }

    protected Trolebus(Parcel in) {
        idTrolebus = in.readInt();
        placa = in.readString();
        codigoQR = in.readString();
        idModelo = in.readInt();
    }

    public static final Creator<Trolebus> CREATOR = new Creator<Trolebus>() {
        @Override
        public Trolebus createFromParcel(Parcel in) {
            return new Trolebus(in);
        }

        @Override
        public Trolebus[] newArray(int size) {
            return new Trolebus[size];
        }
    };

    public Integer getIdTrolebus() {
        return idTrolebus;
    }

    public void setIdTrolebus(Integer idTrolebus) {
        this.idTrolebus = idTrolebus;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCodigoQR() {
        return codigoQR;
    }

    public void setCodigoQR(String codigoQR) {
        this.codigoQR = codigoQR;
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idTrolebus);
        parcel.writeString(placa);
        parcel.writeString(codigoQR);
        parcel.writeInt(idModelo);
    }


}
