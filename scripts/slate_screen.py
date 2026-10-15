"""Minimal strict decoder for Android screencap RGB/RGBA8 PNG pixel checks."""
import struct
import zlib


def pixel(data,x,y):
    if not data.startswith(b'\x89PNG\r\n\x1a\n'):raise ValueError('Not PNG')
    p=8;compressed=[];width=height=channels=None
    while p<len(data):
        size=struct.unpack_from('>I',data,p)[0];kind=data[p+4:p+8];body=data[p+8:p+8+size]
        if len(body)!=size or p+12+size>len(data):raise ValueError('Truncated PNG')
        if zlib.crc32(kind+body)&0xffffffff!=struct.unpack_from('>I',data,p+8+size)[0]:raise ValueError('PNG CRC')
        if kind==b'IHDR':
            width,height,depth,color,compression,filtering,interlace=struct.unpack('>IIBBBBB',body)
            if depth!=8 or color not in (2,6) or compression or filtering or interlace:raise ValueError('Unsupported PNG')
            channels=3 if color==2 else 4
        if kind==b'IDAT':compressed.append(body)
        p+=size+12
    if channels is None or not(0<=x<width and 0<=y<height):raise ValueError('Pixel bounds')
    raw=zlib.decompress(b''.join(compressed));stride=width*channels
    if len(raw)!=(stride+1)*height:raise ValueError('PNG row size')
    previous=bytearray(stride)
    for row in range(y+1):
        offset=row*(stride+1);f=raw[offset];line=bytearray(raw[offset+1:offset+1+stride])
        if f not in range(5):raise ValueError('PNG filter')
        for i in range(stride):
            a=line[i-channels] if i>=channels else 0;b=previous[i];c=previous[i-channels] if i>=channels else 0
            if f==1:v=a
            elif f==2:v=b
            elif f==3:v=(a+b)//2
            elif f==4:
                estimate=a+b-c;pa,pb,pc=abs(estimate-a),abs(estimate-b),abs(estimate-c)
                v=a if pa<=pb and pa<=pc else b if pb<=pc else c
            else:v=0
            line[i]=(line[i]+v)&255
        previous=line
    return tuple(previous[x*channels:x*channels+3])
