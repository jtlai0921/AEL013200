/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\Android\\eclipse\\workspace\\ServiceLauncher\\src\\com\\freejavaman\\BMIInterface.aidl
 */
package com.freejavaman;
public interface BMIInterface extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.freejavaman.BMIInterface
{
private static final java.lang.String DESCRIPTOR = "com.freejavaman.BMIInterface";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.freejavaman.BMIInterface interface,
 * generating a proxy if needed.
 */
public static com.freejavaman.BMIInterface asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.freejavaman.BMIInterface))) {
return ((com.freejavaman.BMIInterface)iin);
}
return new com.freejavaman.BMIInterface.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_setWeight:
{
data.enforceInterface(DESCRIPTOR);
float _arg0;
_arg0 = data.readFloat();
this.setWeight(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_setHeight:
{
data.enforceInterface(DESCRIPTOR);
float _arg0;
_arg0 = data.readFloat();
this.setHeight(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getBMI:
{
data.enforceInterface(DESCRIPTOR);
float _result = this.getBMI();
reply.writeNoException();
reply.writeFloat(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.freejavaman.BMIInterface
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public void setWeight(float w) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeFloat(w);
mRemote.transact(Stub.TRANSACTION_setWeight, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void setHeight(float h) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeFloat(h);
mRemote.transact(Stub.TRANSACTION_setHeight, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public float getBMI() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
float _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getBMI, _data, _reply, 0);
_reply.readException();
_result = _reply.readFloat();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_setWeight = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_setHeight = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getBMI = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
public void setWeight(float w) throws android.os.RemoteException;
public void setHeight(float h) throws android.os.RemoteException;
public float getBMI() throws android.os.RemoteException;
}
