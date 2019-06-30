PGDMP     1    7                w            cine_mas    11.2    11.2 �    G	           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            H	           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            I	           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            J	           1262    16919    cine_mas    DATABASE     �   CREATE DATABASE cine_mas WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Colombia.1252' LC_CTYPE = 'Spanish_Colombia.1252';
    DROP DATABASE cine_mas;
             postgres    false            �            1259    17043    asiento    TABLE     p   CREATE TABLE public.asiento (
    fila text NOT NULL,
    columna text NOT NULL,
    idsala integer NOT NULL
);
    DROP TABLE public.asiento;
       public         postgres    false            �            1259    17222    boleta    TABLE     �   CREATE TABLE public.boleta (
    idboleta integer NOT NULL,
    fila text NOT NULL,
    columna text NOT NULL,
    idfuncion integer NOT NULL,
    idcompra integer NOT NULL,
    idhistoricoboleta integer NOT NULL,
    idsala integer NOT NULL
);
    DROP TABLE public.boleta;
       public         postgres    false            �            1259    17220    boleta_idboleta_seq    SEQUENCE     �   CREATE SEQUENCE public.boleta_idboleta_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.boleta_idboleta_seq;
       public       postgres    false    225            K	           0    0    boleta_idboleta_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.boleta_idboleta_seq OWNED BY public.boleta.idboleta;
            public       postgres    false    224            �            1259    16922    cine    TABLE     u   CREATE TABLE public.cine (
    idcine integer NOT NULL,
    nombrecine text NOT NULL,
    direccion text NOT NULL
);
    DROP TABLE public.cine;
       public         postgres    false            �            1259    16920    cine_idcine_seq    SEQUENCE     �   CREATE SEQUENCE public.cine_idcine_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.cine_idcine_seq;
       public       postgres    false    197            L	           0    0    cine_idcine_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.cine_idcine_seq OWNED BY public.cine.idcine;
            public       postgres    false    196            �            1259    17116    cliente    TABLE       CREATE TABLE public.cliente (
    idcliente integer NOT NULL,
    nombrecliente text NOT NULL,
    apellidocliente text NOT NULL,
    identificacioncliente integer NOT NULL,
    idsuscripcion integer NOT NULL,
    contrasnea text NOT NULL,
    correo text NOT NULL
);
    DROP TABLE public.cliente;
       public         postgres    false            �            1259    17114    cliente_idcliente_seq    SEQUENCE     �   CREATE SEQUENCE public.cliente_idcliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.cliente_idcliente_seq;
       public       postgres    false    219            M	           0    0    cliente_idcliente_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.cliente_idcliente_seq OWNED BY public.cliente.idcliente;
            public       postgres    false    218            �            1259    17094    combo    TABLE     �   CREATE TABLE public.combo (
    idcombo integer NOT NULL,
    descripcion text NOT NULL,
    precio numeric NOT NULL,
    estado integer NOT NULL,
    img bytea NOT NULL
);
    DROP TABLE public.combo;
       public         postgres    false            �            1259    17092    combo_idcombo_seq    SEQUENCE     �   CREATE SEQUENCE public.combo_idcombo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.combo_idcombo_seq;
       public       postgres    false    215            N	           0    0    combo_idcombo_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.combo_idcombo_seq OWNED BY public.combo.idcombo;
            public       postgres    false    214            �            1259    17246    combocompra    TABLE     �   CREATE TABLE public.combocompra (
    cantidad integer NOT NULL,
    subtotal numeric NOT NULL,
    idcompra integer NOT NULL,
    idcombo integer NOT NULL
);
    DROP TABLE public.combocompra;
       public         postgres    false            �            1259    17206    compra    TABLE     �   CREATE TABLE public.compra (
    idcompra integer NOT NULL,
    fecha date NOT NULL,
    idcliente integer NOT NULL,
    total numeric NOT NULL
);
    DROP TABLE public.compra;
       public         postgres    false            �            1259    17204    compra_idcompra_seq    SEQUENCE     �   CREATE SEQUENCE public.compra_idcompra_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.compra_idcompra_seq;
       public       postgres    false    223            O	           0    0    compra_idcompra_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.compra_idcompra_seq OWNED BY public.compra.idcompra;
            public       postgres    false    222            �            1259    16942    empleado    TABLE       CREATE TABLE public.empleado (
    idempleado integer NOT NULL,
    nombreempleado text NOT NULL,
    apellidoempleado text NOT NULL,
    identificacionempleado integer NOT NULL,
    idcine integer,
    estado integer NOT NULL,
    idtipoempleado integer NOT NULL
);
    DROP TABLE public.empleado;
       public         postgres    false            �            1259    17074    espacio    TABLE     �   CREATE TABLE public.espacio (
    idfuncion integer NOT NULL,
    fila text NOT NULL,
    columna text NOT NULL,
    idsala integer NOT NULL
);
    DROP TABLE public.espacio;
       public         postgres    false            �            1259    17058    funcion    TABLE     �   CREATE TABLE public.funcion (
    idfuncion integer NOT NULL,
    idpelicula integer NOT NULL,
    fecha date NOT NULL,
    hora interval NOT NULL,
    idsala integer NOT NULL,
    estado integer NOT NULL
);
    DROP TABLE public.funcion;
       public         postgres    false            �            1259    17056    funcion_idfuncion_seq    SEQUENCE     �   CREATE SEQUENCE public.funcion_idfuncion_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.funcion_idfuncion_seq;
       public       postgres    false    212            P	           0    0    funcion_idfuncion_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.funcion_idfuncion_seq OWNED BY public.funcion.idfuncion;
            public       postgres    false    211            �            1259    17132    historicopreciosboleta    TABLE     �   CREATE TABLE public.historicopreciosboleta (
    idhistoricoboleta integer NOT NULL,
    precio numeric NOT NULL,
    fecha date NOT NULL
);
 *   DROP TABLE public.historicopreciosboleta;
       public         postgres    false            �            1259    17130 ,   historicopreciosboleta_idhistoricoboleta_seq    SEQUENCE     �   CREATE SEQUENCE public.historicopreciosboleta_idhistoricoboleta_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 C   DROP SEQUENCE public.historicopreciosboleta_idhistoricoboleta_seq;
       public       postgres    false    221            Q	           0    0 ,   historicopreciosboleta_idhistoricoboleta_seq    SEQUENCE OWNED BY     }   ALTER SEQUENCE public.historicopreciosboleta_idhistoricoboleta_seq OWNED BY public.historicopreciosboleta.idhistoricoboleta;
            public       postgres    false    220            �            1259    17004 
   inventario    TABLE     �   CREATE TABLE public.inventario (
    cantidad integer NOT NULL,
    idcine integer NOT NULL,
    idproducto integer NOT NULL
);
    DROP TABLE public.inventario;
       public         postgres    false            �            1259    17021    pelicula    TABLE     �   CREATE TABLE public.pelicula (
    idpelicula integer NOT NULL,
    nombrepelicula text NOT NULL,
    sinopsis text NOT NULL,
    fechaestreno date NOT NULL,
    duracion interval NOT NULL,
    nombredirector text NOT NULL,
    img bytea NOT NULL
);
    DROP TABLE public.pelicula;
       public         postgres    false            �            1259    17019    pelicula_idpelicula_seq    SEQUENCE     �   CREATE SEQUENCE public.pelicula_idpelicula_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.pelicula_idpelicula_seq;
       public       postgres    false    207            R	           0    0    pelicula_idpelicula_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.pelicula_idpelicula_seq OWNED BY public.pelicula.idpelicula;
            public       postgres    false    206            �            1259    16996    producto    TABLE     ~   CREATE TABLE public.producto (
    idproducto integer NOT NULL,
    nombre text NOT NULL,
    unidadmedicion text NOT NULL
);
    DROP TABLE public.producto;
       public         postgres    false            �            1259    17032    sala    TABLE     y   CREATE TABLE public.sala (
    idsala integer NOT NULL,
    consecutivo integer NOT NULL,
    idcine integer NOT NULL
);
    DROP TABLE public.sala;
       public         postgres    false            �            1259    17030    sala_idsala_seq    SEQUENCE     �   CREATE SEQUENCE public.sala_idsala_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.sala_idsala_seq;
       public       postgres    false    209            S	           0    0    sala_idsala_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.sala_idsala_seq OWNED BY public.sala.idsala;
            public       postgres    false    208            �            1259    16933    tipoempleado    TABLE     i   CREATE TABLE public.tipoempleado (
    idtipoempleado integer NOT NULL,
    descripcion text NOT NULL
);
     DROP TABLE public.tipoempleado;
       public         postgres    false            �            1259    16931    tipoempleado_idtipoempleado_seq    SEQUENCE     �   CREATE SEQUENCE public.tipoempleado_idtipoempleado_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.tipoempleado_idtipoempleado_seq;
       public       postgres    false    199            T	           0    0    tipoempleado_idtipoempleado_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.tipoempleado_idtipoempleado_seq OWNED BY public.tipoempleado.idtipoempleado;
            public       postgres    false    198            �            1259    17105    tiposuscripcion    TABLE     �   CREATE TABLE public.tiposuscripcion (
    idsuscripcion integer NOT NULL,
    nombre text NOT NULL,
    descripcion text NOT NULL
);
 #   DROP TABLE public.tiposuscripcion;
       public         postgres    false            �            1259    17103 !   tiposuscripcion_idsuscripcion_seq    SEQUENCE     �   CREATE SEQUENCE public.tiposuscripcion_idsuscripcion_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 8   DROP SEQUENCE public.tiposuscripcion_idsuscripcion_seq;
       public       postgres    false    217            U	           0    0 !   tiposuscripcion_idsuscripcion_seq    SEQUENCE OWNED BY     g   ALTER SEQUENCE public.tiposuscripcion_idsuscripcion_seq OWNED BY public.tiposuscripcion.idsuscripcion;
            public       postgres    false    216            �            1259    16964    tipousuario    TABLE     g   CREATE TABLE public.tipousuario (
    idtipousuario integer NOT NULL,
    descripcion text NOT NULL
);
    DROP TABLE public.tipousuario;
       public         postgres    false            �            1259    16962    tipousuario_idtipousuario_seq    SEQUENCE     �   CREATE SEQUENCE public.tipousuario_idtipousuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.tipousuario_idtipousuario_seq;
       public       postgres    false    202            V	           0    0    tipousuario_idtipousuario_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.tipousuario_idtipousuario_seq OWNED BY public.tipousuario.idtipousuario;
            public       postgres    false    201            �            1259    16973    usuario    TABLE     �   CREATE TABLE public.usuario (
    idusuario text NOT NULL,
    contrasena text NOT NULL,
    idempleado integer NOT NULL,
    idtipousuario integer NOT NULL,
    idcineencargado integer
);
    DROP TABLE public.usuario;
       public         postgres    false            p           2604    17225    boleta idboleta    DEFAULT     r   ALTER TABLE ONLY public.boleta ALTER COLUMN idboleta SET DEFAULT nextval('public.boleta_idboleta_seq'::regclass);
 >   ALTER TABLE public.boleta ALTER COLUMN idboleta DROP DEFAULT;
       public       postgres    false    225    224    225            e           2604    16925    cine idcine    DEFAULT     j   ALTER TABLE ONLY public.cine ALTER COLUMN idcine SET DEFAULT nextval('public.cine_idcine_seq'::regclass);
 :   ALTER TABLE public.cine ALTER COLUMN idcine DROP DEFAULT;
       public       postgres    false    196    197    197            m           2604    17119    cliente idcliente    DEFAULT     v   ALTER TABLE ONLY public.cliente ALTER COLUMN idcliente SET DEFAULT nextval('public.cliente_idcliente_seq'::regclass);
 @   ALTER TABLE public.cliente ALTER COLUMN idcliente DROP DEFAULT;
       public       postgres    false    218    219    219            k           2604    17097    combo idcombo    DEFAULT     n   ALTER TABLE ONLY public.combo ALTER COLUMN idcombo SET DEFAULT nextval('public.combo_idcombo_seq'::regclass);
 <   ALTER TABLE public.combo ALTER COLUMN idcombo DROP DEFAULT;
       public       postgres    false    215    214    215            o           2604    17209    compra idcompra    DEFAULT     r   ALTER TABLE ONLY public.compra ALTER COLUMN idcompra SET DEFAULT nextval('public.compra_idcompra_seq'::regclass);
 >   ALTER TABLE public.compra ALTER COLUMN idcompra DROP DEFAULT;
       public       postgres    false    222    223    223            j           2604    17061    funcion idfuncion    DEFAULT     v   ALTER TABLE ONLY public.funcion ALTER COLUMN idfuncion SET DEFAULT nextval('public.funcion_idfuncion_seq'::regclass);
 @   ALTER TABLE public.funcion ALTER COLUMN idfuncion DROP DEFAULT;
       public       postgres    false    212    211    212            n           2604    17135 (   historicopreciosboleta idhistoricoboleta    DEFAULT     �   ALTER TABLE ONLY public.historicopreciosboleta ALTER COLUMN idhistoricoboleta SET DEFAULT nextval('public.historicopreciosboleta_idhistoricoboleta_seq'::regclass);
 W   ALTER TABLE public.historicopreciosboleta ALTER COLUMN idhistoricoboleta DROP DEFAULT;
       public       postgres    false    220    221    221            h           2604    17024    pelicula idpelicula    DEFAULT     z   ALTER TABLE ONLY public.pelicula ALTER COLUMN idpelicula SET DEFAULT nextval('public.pelicula_idpelicula_seq'::regclass);
 B   ALTER TABLE public.pelicula ALTER COLUMN idpelicula DROP DEFAULT;
       public       postgres    false    206    207    207            i           2604    17035    sala idsala    DEFAULT     j   ALTER TABLE ONLY public.sala ALTER COLUMN idsala SET DEFAULT nextval('public.sala_idsala_seq'::regclass);
 :   ALTER TABLE public.sala ALTER COLUMN idsala DROP DEFAULT;
       public       postgres    false    209    208    209            f           2604    16936    tipoempleado idtipoempleado    DEFAULT     �   ALTER TABLE ONLY public.tipoempleado ALTER COLUMN idtipoempleado SET DEFAULT nextval('public.tipoempleado_idtipoempleado_seq'::regclass);
 J   ALTER TABLE public.tipoempleado ALTER COLUMN idtipoempleado DROP DEFAULT;
       public       postgres    false    199    198    199            l           2604    17108    tiposuscripcion idsuscripcion    DEFAULT     �   ALTER TABLE ONLY public.tiposuscripcion ALTER COLUMN idsuscripcion SET DEFAULT nextval('public.tiposuscripcion_idsuscripcion_seq'::regclass);
 L   ALTER TABLE public.tiposuscripcion ALTER COLUMN idsuscripcion DROP DEFAULT;
       public       postgres    false    216    217    217            g           2604    16967    tipousuario idtipousuario    DEFAULT     �   ALTER TABLE ONLY public.tipousuario ALTER COLUMN idtipousuario SET DEFAULT nextval('public.tipousuario_idtipousuario_seq'::regclass);
 H   ALTER TABLE public.tipousuario ALTER COLUMN idtipousuario DROP DEFAULT;
       public       postgres    false    202    201    202            4	          0    17043    asiento 
   TABLE DATA               8   COPY public.asiento (fila, columna, idsala) FROM stdin;
    public       postgres    false    210   ~�       C	          0    17222    boleta 
   TABLE DATA               i   COPY public.boleta (idboleta, fila, columna, idfuncion, idcompra, idhistoricoboleta, idsala) FROM stdin;
    public       postgres    false    225   a�       '	          0    16922    cine 
   TABLE DATA               =   COPY public.cine (idcine, nombrecine, direccion) FROM stdin;
    public       postgres    false    197   ~�       =	          0    17116    cliente 
   TABLE DATA               �   COPY public.cliente (idcliente, nombrecliente, apellidocliente, identificacioncliente, idsuscripcion, contrasnea, correo) FROM stdin;
    public       postgres    false    219   ��       9	          0    17094    combo 
   TABLE DATA               J   COPY public.combo (idcombo, descripcion, precio, estado, img) FROM stdin;
    public       postgres    false    215   ��       D	          0    17246    combocompra 
   TABLE DATA               L   COPY public.combocompra (cantidad, subtotal, idcompra, idcombo) FROM stdin;
    public       postgres    false    226   �       A	          0    17206    compra 
   TABLE DATA               C   COPY public.compra (idcompra, fecha, idcliente, total) FROM stdin;
    public       postgres    false    223   /�       *	          0    16942    empleado 
   TABLE DATA               �   COPY public.empleado (idempleado, nombreempleado, apellidoempleado, identificacionempleado, idcine, estado, idtipoempleado) FROM stdin;
    public       postgres    false    200   L�       7	          0    17074    espacio 
   TABLE DATA               C   COPY public.espacio (idfuncion, fila, columna, idsala) FROM stdin;
    public       postgres    false    213   ��       6	          0    17058    funcion 
   TABLE DATA               U   COPY public.funcion (idfuncion, idpelicula, fecha, hora, idsala, estado) FROM stdin;
    public       postgres    false    212   ��       ?	          0    17132    historicopreciosboleta 
   TABLE DATA               R   COPY public.historicopreciosboleta (idhistoricoboleta, precio, fecha) FROM stdin;
    public       postgres    false    221   �       /	          0    17004 
   inventario 
   TABLE DATA               B   COPY public.inventario (cantidad, idcine, idproducto) FROM stdin;
    public       postgres    false    205   B�       1	          0    17021    pelicula 
   TABLE DATA               u   COPY public.pelicula (idpelicula, nombrepelicula, sinopsis, fechaestreno, duracion, nombredirector, img) FROM stdin;
    public       postgres    false    207   z�       .	          0    16996    producto 
   TABLE DATA               F   COPY public.producto (idproducto, nombre, unidadmedicion) FROM stdin;
    public       postgres    false    204   ��       3	          0    17032    sala 
   TABLE DATA               ;   COPY public.sala (idsala, consecutivo, idcine) FROM stdin;
    public       postgres    false    209   ��       )	          0    16933    tipoempleado 
   TABLE DATA               C   COPY public.tipoempleado (idtipoempleado, descripcion) FROM stdin;
    public       postgres    false    199   ��       ;	          0    17105    tiposuscripcion 
   TABLE DATA               M   COPY public.tiposuscripcion (idsuscripcion, nombre, descripcion) FROM stdin;
    public       postgres    false    217   -�       ,	          0    16964    tipousuario 
   TABLE DATA               A   COPY public.tipousuario (idtipousuario, descripcion) FROM stdin;
    public       postgres    false    202   ��       -	          0    16973    usuario 
   TABLE DATA               d   COPY public.usuario (idusuario, contrasena, idempleado, idtipousuario, idcineencargado) FROM stdin;
    public       postgres    false    203   �       W	           0    0    boleta_idboleta_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.boleta_idboleta_seq', 4, true);
            public       postgres    false    224            X	           0    0    cine_idcine_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.cine_idcine_seq', 10, true);
            public       postgres    false    196            Y	           0    0    cliente_idcliente_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.cliente_idcliente_seq', 101, true);
            public       postgres    false    218            Z	           0    0    combo_idcombo_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.combo_idcombo_seq', 2, true);
            public       postgres    false    214            [	           0    0    compra_idcompra_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.compra_idcompra_seq', 2, true);
            public       postgres    false    222            \	           0    0    funcion_idfuncion_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.funcion_idfuncion_seq', 4500, true);
            public       postgres    false    211            ]	           0    0 ,   historicopreciosboleta_idhistoricoboleta_seq    SEQUENCE SET     Z   SELECT pg_catalog.setval('public.historicopreciosboleta_idhistoricoboleta_seq', 1, true);
            public       postgres    false    220            ^	           0    0    pelicula_idpelicula_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.pelicula_idpelicula_seq', 1, true);
            public       postgres    false    206            _	           0    0    sala_idsala_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.sala_idsala_seq', 100, true);
            public       postgres    false    208            `	           0    0    tipoempleado_idtipoempleado_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.tipoempleado_idtipoempleado_seq', 2, true);
            public       postgres    false    198            a	           0    0 !   tiposuscripcion_idsuscripcion_seq    SEQUENCE SET     P   SELECT pg_catalog.setval('public.tiposuscripcion_idsuscripcion_seq', 1, false);
            public       postgres    false    216            b	           0    0    tipousuario_idtipousuario_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.tipousuario_idtipousuario_seq', 2, true);
            public       postgres    false    201            �           2606    17230    boleta boleta_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.boleta
    ADD CONSTRAINT boleta_pkey PRIMARY KEY (idboleta);
 <   ALTER TABLE ONLY public.boleta DROP CONSTRAINT boleta_pkey;
       public         postgres    false    225            r           2606    16930    cine cine_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.cine
    ADD CONSTRAINT cine_pkey PRIMARY KEY (idcine);
 8   ALTER TABLE ONLY public.cine DROP CONSTRAINT cine_pkey;
       public         postgres    false    197            �           2606    17124    cliente cliente_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (idcliente);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public         postgres    false    219            �           2606    17102    combo combo_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.combo
    ADD CONSTRAINT combo_pkey PRIMARY KEY (idcombo);
 :   ALTER TABLE ONLY public.combo DROP CONSTRAINT combo_pkey;
       public         postgres    false    215            �           2606    17214    compra compra_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT compra_pkey PRIMARY KEY (idcompra);
 <   ALTER TABLE ONLY public.compra DROP CONSTRAINT compra_pkey;
       public         postgres    false    223            v           2606    16951 ,   empleado empleado_identificacionempleado_key 
   CONSTRAINT     y   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_identificacionempleado_key UNIQUE (identificacionempleado);
 V   ALTER TABLE ONLY public.empleado DROP CONSTRAINT empleado_identificacionempleado_key;
       public         postgres    false    200            x           2606    16949    empleado empleado_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (idempleado);
 @   ALTER TABLE ONLY public.empleado DROP CONSTRAINT empleado_pkey;
       public         postgres    false    200            �           2606    17063    funcion funcion_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.funcion
    ADD CONSTRAINT funcion_pkey PRIMARY KEY (idfuncion);
 >   ALTER TABLE ONLY public.funcion DROP CONSTRAINT funcion_pkey;
       public         postgres    false    212            �           2606    17140 2   historicopreciosboleta historicopreciosboleta_pkey 
   CONSTRAINT        ALTER TABLE ONLY public.historicopreciosboleta
    ADD CONSTRAINT historicopreciosboleta_pkey PRIMARY KEY (idhistoricoboleta);
 \   ALTER TABLE ONLY public.historicopreciosboleta DROP CONSTRAINT historicopreciosboleta_pkey;
       public         postgres    false    221            �           2606    17029    pelicula pelicula_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.pelicula
    ADD CONSTRAINT pelicula_pkey PRIMARY KEY (idpelicula);
 @   ALTER TABLE ONLY public.pelicula DROP CONSTRAINT pelicula_pkey;
       public         postgres    false    207            �           2606    17050    asiento pk_asiento 
   CONSTRAINT     c   ALTER TABLE ONLY public.asiento
    ADD CONSTRAINT pk_asiento PRIMARY KEY (fila, columna, idsala);
 <   ALTER TABLE ONLY public.asiento DROP CONSTRAINT pk_asiento;
       public         postgres    false    210    210    210            �           2606    17253    combocompra pk_combocompra 
   CONSTRAINT     g   ALTER TABLE ONLY public.combocompra
    ADD CONSTRAINT pk_combocompra PRIMARY KEY (idcompra, idcombo);
 D   ALTER TABLE ONLY public.combocompra DROP CONSTRAINT pk_combocompra;
       public         postgres    false    226    226            �           2606    17081    espacio pk_espacio 
   CONSTRAINT     n   ALTER TABLE ONLY public.espacio
    ADD CONSTRAINT pk_espacio PRIMARY KEY (idfuncion, fila, columna, idsala);
 <   ALTER TABLE ONLY public.espacio DROP CONSTRAINT pk_espacio;
       public         postgres    false    213    213    213    213            �           2606    17008    inventario pk_inventario 
   CONSTRAINT     f   ALTER TABLE ONLY public.inventario
    ADD CONSTRAINT pk_inventario PRIMARY KEY (idcine, idproducto);
 B   ALTER TABLE ONLY public.inventario DROP CONSTRAINT pk_inventario;
       public         postgres    false    205    205            ~           2606    17003    producto producto_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (idproducto);
 @   ALTER TABLE ONLY public.producto DROP CONSTRAINT producto_pkey;
       public         postgres    false    204            �           2606    17037    sala sala_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.sala
    ADD CONSTRAINT sala_pkey PRIMARY KEY (idsala);
 8   ALTER TABLE ONLY public.sala DROP CONSTRAINT sala_pkey;
       public         postgres    false    209            t           2606    16941    tipoempleado tipoempleado_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.tipoempleado
    ADD CONSTRAINT tipoempleado_pkey PRIMARY KEY (idtipoempleado);
 H   ALTER TABLE ONLY public.tipoempleado DROP CONSTRAINT tipoempleado_pkey;
       public         postgres    false    199            �           2606    17113 $   tiposuscripcion tiposuscripcion_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY public.tiposuscripcion
    ADD CONSTRAINT tiposuscripcion_pkey PRIMARY KEY (idsuscripcion);
 N   ALTER TABLE ONLY public.tiposuscripcion DROP CONSTRAINT tiposuscripcion_pkey;
       public         postgres    false    217            z           2606    16972    tipousuario tipousuario_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public.tipousuario
    ADD CONSTRAINT tipousuario_pkey PRIMARY KEY (idtipousuario);
 F   ALTER TABLE ONLY public.tipousuario DROP CONSTRAINT tipousuario_pkey;
       public         postgres    false    202            |           2606    16980    usuario usuario_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (idusuario);
 >   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_pkey;
       public         postgres    false    203            �           2606    17051    asiento fk_asiento_sala    FK CONSTRAINT     x   ALTER TABLE ONLY public.asiento
    ADD CONSTRAINT fk_asiento_sala FOREIGN KEY (idsala) REFERENCES public.sala(idsala);
 A   ALTER TABLE ONLY public.asiento DROP CONSTRAINT fk_asiento_sala;
       public       postgres    false    2180    210    209            �           2606    17236    boleta fk_boleta_compra    FK CONSTRAINT     ~   ALTER TABLE ONLY public.boleta
    ADD CONSTRAINT fk_boleta_compra FOREIGN KEY (idcompra) REFERENCES public.compra(idcompra);
 A   ALTER TABLE ONLY public.boleta DROP CONSTRAINT fk_boleta_compra;
       public       postgres    false    225    223    2196            �           2606    17231    boleta fk_boleta_espacio    FK CONSTRAINT     �   ALTER TABLE ONLY public.boleta
    ADD CONSTRAINT fk_boleta_espacio FOREIGN KEY (idfuncion, idsala, fila, columna) REFERENCES public.espacio(idfuncion, idsala, fila, columna);
 B   ALTER TABLE ONLY public.boleta DROP CONSTRAINT fk_boleta_espacio;
       public       postgres    false    213    2186    213    213    213    225    225    225    225            �           2606    17241    boleta fk_boleta_historico    FK CONSTRAINT     �   ALTER TABLE ONLY public.boleta
    ADD CONSTRAINT fk_boleta_historico FOREIGN KEY (idhistoricoboleta) REFERENCES public.historicopreciosboleta(idhistoricoboleta);
 D   ALTER TABLE ONLY public.boleta DROP CONSTRAINT fk_boleta_historico;
       public       postgres    false    2194    225    221            �           2606    17125 "   cliente fk_cliente_tiposuscripcion    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fk_cliente_tiposuscripcion FOREIGN KEY (idsuscripcion) REFERENCES public.tiposuscripcion(idsuscripcion);
 L   ALTER TABLE ONLY public.cliente DROP CONSTRAINT fk_cliente_tiposuscripcion;
       public       postgres    false    219    2190    217            �           2606    17254     combocompra fk_combocompra_combo    FK CONSTRAINT     �   ALTER TABLE ONLY public.combocompra
    ADD CONSTRAINT fk_combocompra_combo FOREIGN KEY (idcombo) REFERENCES public.combo(idcombo);
 J   ALTER TABLE ONLY public.combocompra DROP CONSTRAINT fk_combocompra_combo;
       public       postgres    false    226    2188    215            �           2606    17259 !   combocompra fk_combocompra_compra    FK CONSTRAINT     �   ALTER TABLE ONLY public.combocompra
    ADD CONSTRAINT fk_combocompra_compra FOREIGN KEY (idcompra) REFERENCES public.compra(idcompra);
 K   ALTER TABLE ONLY public.combocompra DROP CONSTRAINT fk_combocompra_compra;
       public       postgres    false    2196    226    223            �           2606    17215    compra fk_compra_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT fk_compra_cliente FOREIGN KEY (idcliente) REFERENCES public.cliente(idcliente);
 B   ALTER TABLE ONLY public.compra DROP CONSTRAINT fk_compra_cliente;
       public       postgres    false    2192    219    223            �           2606    16952    empleado fk_empleado_cine    FK CONSTRAINT     z   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT fk_empleado_cine FOREIGN KEY (idcine) REFERENCES public.cine(idcine);
 C   ALTER TABLE ONLY public.empleado DROP CONSTRAINT fk_empleado_cine;
       public       postgres    false    197    2162    200            �           2606    16957 !   empleado fk_empleado_tipoempleado    FK CONSTRAINT     �   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT fk_empleado_tipoempleado FOREIGN KEY (idtipoempleado) REFERENCES public.tipoempleado(idtipoempleado);
 K   ALTER TABLE ONLY public.empleado DROP CONSTRAINT fk_empleado_tipoempleado;
       public       postgres    false    200    199    2164            �           2606    17087    espacio fk_espacio_asiento    FK CONSTRAINT     �   ALTER TABLE ONLY public.espacio
    ADD CONSTRAINT fk_espacio_asiento FOREIGN KEY (fila, columna, idsala) REFERENCES public.asiento(fila, columna, idsala);
 D   ALTER TABLE ONLY public.espacio DROP CONSTRAINT fk_espacio_asiento;
       public       postgres    false    210    213    213    213    210    2182    210            �           2606    17082    espacio fk_espacio_funcion    FK CONSTRAINT     �   ALTER TABLE ONLY public.espacio
    ADD CONSTRAINT fk_espacio_funcion FOREIGN KEY (idfuncion) REFERENCES public.funcion(idfuncion);
 D   ALTER TABLE ONLY public.espacio DROP CONSTRAINT fk_espacio_funcion;
       public       postgres    false    213    212    2184            �           2606    17009    inventario fk_inventario_cine    FK CONSTRAINT     ~   ALTER TABLE ONLY public.inventario
    ADD CONSTRAINT fk_inventario_cine FOREIGN KEY (idcine) REFERENCES public.cine(idcine);
 G   ALTER TABLE ONLY public.inventario DROP CONSTRAINT fk_inventario_cine;
       public       postgres    false    2162    205    197            �           2606    17014 !   inventario fk_inventario_producto    FK CONSTRAINT     �   ALTER TABLE ONLY public.inventario
    ADD CONSTRAINT fk_inventario_producto FOREIGN KEY (idproducto) REFERENCES public.producto(idproducto);
 K   ALTER TABLE ONLY public.inventario DROP CONSTRAINT fk_inventario_producto;
       public       postgres    false    205    204    2174            �           2606    17038    sala fk_sala_cine    FK CONSTRAINT     r   ALTER TABLE ONLY public.sala
    ADD CONSTRAINT fk_sala_cine FOREIGN KEY (idcine) REFERENCES public.cine(idcine);
 ;   ALTER TABLE ONLY public.sala DROP CONSTRAINT fk_sala_cine;
       public       postgres    false    197    2162    209            �           2606    16991    usuario fk_usuario_cine    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk_usuario_cine FOREIGN KEY (idcineencargado) REFERENCES public.cine(idcine);
 A   ALTER TABLE ONLY public.usuario DROP CONSTRAINT fk_usuario_cine;
       public       postgres    false    203    197    2162            �           2606    16981    usuario fk_usuario_empleado    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk_usuario_empleado FOREIGN KEY (idempleado) REFERENCES public.empleado(idempleado);
 E   ALTER TABLE ONLY public.usuario DROP CONSTRAINT fk_usuario_empleado;
       public       postgres    false    200    203    2168            �           2606    16986    usuario fk_usuario_tipousuario    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk_usuario_tipousuario FOREIGN KEY (idtipousuario) REFERENCES public.tipousuario(idtipousuario);
 H   ALTER TABLE ONLY public.usuario DROP CONSTRAINT fk_usuario_tipousuario;
       public       postgres    false    203    202    2170            �           2606    17064    funcion pk_funcion_pelicula    FK CONSTRAINT     �   ALTER TABLE ONLY public.funcion
    ADD CONSTRAINT pk_funcion_pelicula FOREIGN KEY (idpelicula) REFERENCES public.pelicula(idpelicula);
 E   ALTER TABLE ONLY public.funcion DROP CONSTRAINT pk_funcion_pelicula;
       public       postgres    false    2178    207    212            �           2606    17069    funcion pk_funcion_sala    FK CONSTRAINT     x   ALTER TABLE ONLY public.funcion
    ADD CONSTRAINT pk_funcion_sala FOREIGN KEY (idsala) REFERENCES public.sala(idsala);
 A   ALTER TABLE ONLY public.funcion DROP CONSTRAINT pk_funcion_sala;
       public       postgres    false    2180    209    212            4	      x�E�;�d��`Q;nc
�/ɾ�S���=��4��t���@���������������������x���ޟ��ߟ������y��>��}�������?������������������}��>��}�������>��>��>��>��>��>��>��>��>��>���?��?��?��?��?��?��?��?��?��?>��>��>��>��>��>��>��>��>��>�������������������������������>��?�g�T�/�|��K5_��R͗jJ5_��R͗j�T�/�|��K5_�)�|��K5_��R͗j�T�/�|��T�/�|��K5_��R͗j�T�R͗j�T�/�|��K5_��R͗jJ5_��R͗j�T�/�|��K5_�)�|��K5_��R͗j�T�/�|��T���^��R��j�T�Z/�z��K��Z/�z��K�^��R��j�T�Z/Ւj�T�Z/�z��K�^��R��j�TK��R��j�T�Z/�z��K�^��R-��K�^��R��j�T�Z/�z��K��Z/�z��K�^��R��j�T�Z/Ւj�T�Z/�z��K�^��R��j�TK�����/�~��K�_��R�j�T���R�j�T���/�~��K�_��R�jK�_��R�j�T���/�~��K�_�-�~��K�_��R�j�T���/�~��T���/�~��K�_��R�j�T���R�j�T���/�~��K�_��R�jK�_��R�j�T���/�~��K�_�-��G����T�:/�y��Ku^��R��Ku^��R���T�:/�y��Ku�:/�y��Ku^��R���T�:/Ց�T�:/�y��Ku^��R���TG��R���T�:/�y��Ku^��R��Ku^��R���T�:/�y��Ku�:/�y��Ku^��R���T�:/Ց����Ku_��Rݗ�T���/�}��T���/�}��Ku_��Rݗ�T���Rݗ�T���/�}��Ku_��Rݗ�Ju_��Rݗ�T���/�}��Ku_�+�}��Ku_��Rݗ�T���/�}��T���/�}��Ku_��Rݗ�T���Rݗ�T���/�}��Ku_��Rݗ�J���?�R�K/U�T�R�K/U�T!U�T�R�K/U�T�R�K/U�T!U�T�R�K/U�T�R�K/U�T!U�T�R�K/U�T�R�K/U�T!U�T�R�K/U�T�R�K/U�T!U�T�R�K/U�T�R�K/U�T!U�T�R�K/U�T�R�K/U�T!��G��K�/U�T�R�K�/U�T�R�T�R�K�/U�T�R�K�/U�T�R�T�R�K�/U�T�R�K�/U�T�R�T�R�K�/U�T�R�K�/U�T�R�T�R�K�/U�T�R�K�/U�T�R�T�R�K�/U�T�R�K�/U�T�R�T�R�K�/U�T�R�K�/U�T�R�T��S/U�T�R�KU/U�T�R�KUR�KU/U�T�R�KU/U�T�R�KUR�KU/U�T�R�KU/U�T�R�KUR�KU/U�T�R�KU/U�T�R�KUR�KU/U�T�R�KU/U�T�R�KUR�KU/U�T�R�KU/U�T�R�KUR�KU/U�T�R�KU/U�T�R�KUR������k����탿6�_;ᯭ���_�����v�k?��!��m����_��o��_�⯍����kk��7��펿����m��v�_[�=��&�k���M��'m���S��*핿6�_�����~�k���c��2{���i��5m����_篝����k���y����k�����@�����_{�M��.�k��}�W�F�V�f�v����������h�h�h�h iiiii�a�v���������Ɛ֐���iiiiiiiii�����Ƒ֑�����&�6�$i%i&i'i(i)i*i+i,i-������&�6�F�V�f�v�8!'�v2�ɠ'�~2 � (�	e0�QEep�RI(e��ј2h��)���2��@*���2��hV\e��AVZle��AW^|e4��2�`,��20��,��2�Zk�eЖ�[o�e��\s�.���2�� /���2�ˀ/�����>�3�`0�
30��0�!f���b�0fИ�c�@f��$3�̀2��,3�� 3��43��h�tf���g��f ��h��f4�N3@� 5�V3`͠5�^3l��f0�m��fp�n�M7��x3���7���3�@8��F�>��3@� 9��3`Π9�9g�t�Hg0�u�Xgp�Ѱ3��@;���3���;���3�h��g@�Ay��g��Az��g4��3p��=��3��`>��3�}��g���~��gП��@� 4Ѐ@�48� A��� �>4p��A"4��`B�QhP���`h����phС�<4�� D�D4рD�L48�h(�h����,�h���0�h4f4�ѠFn4�� Gv4�=�h��Ai0��E�H�#�4H�@I�%�4h��I�'�%�>)�4�ҠJ+�4�� K�ii���]xi��a�i0���4(��L�3�4H�@M�5�4h�hn�i �A�r�i@�A�v�i4<�4��`O>�4���O@5���jP���� j������Q�F5x� R�H$5�ԀR�J�f)�>L25��`SN:5���S��j�����jP�����j���T5XՀU�V\5x� V�Xd5��h��j`�����j������j4_~5 � Xa�5 ֠Xc�5��(k����8k���MZ�i�5���Z�k�5��@[�m��-�>o�5 � \q�5 נ\��kp����k�����k���5��@^�y�5���^�{�5��h��k��A���k �A�l4�
60��`�6P�`a�6��@l���PlP���c��46�؀c��<6�� d���&2�>�J60��d�R6P�`e��lв��/�l���3�lP��l6�� g��t6�ـg��|6��h@m ���Dm`���H$m4��6`ڠi��6�� j��6�UXmp��Yhm���]�k���6�@l���6(��l����6�>��M�6I�$m��M�6I�$m��m��I�&i��m��I�&i��m����6I�$m��M�6I�$m��M�6I�li��m��I�&i��m��I�&i��m��M�6I�$m��M�6I�$m��M�6[�&i��m��I�&i��m��I�&i�-m��M�6I�$m��M�6I�$m��͖6�>��M�6I�$m��M�6I�$m��m��I�&i��m��I�&i��m����6I�$m��M�6I�$m��M�6I�Dc�wg�M�6��n�M�6��n����U��n�M�6��n�M�6�&p��m�	�&p��m�	�&p�n�M�6��n�M�6��n����G���RV��J}\��+���>��G�~�,���>��ǖ��R\�K}t��.�ᥟ�K}|��/��>��G��Sb�SL}���Sd�L}���2�a�>��Ǚ�<Sh�9��G��LSj�SM}���5���>��G�~�6��>��Ǜ�|Sp�N}ĩ�8�!��SN}̩�9�A�>��G���Sv��N}���p��m�	�&p��m�	�&p�n�M�6��n�M�6��n���	�&p��m�	�&p��m�	�f��n�M�6��n�M�6��n��m�	�&p��m�	�&p��m���6��n�M�6��n�M�6��lp��m�	�&p��m�	�&p��m6�)�?����m�	�&p��m�	�f��n�M�6��n�M�6��n�����~���g�9~���~��Kn�M�6��n�M�6��n��m��I�&i��m��I�&i��m��M�6I�$m��M�6I�$m��M�6[�&i��m��I�&i��m��I�&i�-mR}&i��m��I�&i��m��I�fK�$m��M�6I�$m��M�6I�$m��m��I�&i��m��I�&i��m����6I�$m��M�6I�$m��M�6I�li��m��I�&i��m    ��I�&i��m��M�6I�$m��M�6I�$m��M�6[�&i��m��I�&i��m��I�&i�-m�}&i��m��I�&i��m��I�fK�$m��M�6I�$m��M�6I�$m��m��I�&i��m��I�&i��m����6I�$m��M�6I�$m��M�6I�li��m��I�&i��m��I�&i��m��M�6I�$m��M�6I�$m��M�6[�&i��m��I�&i��m��I�&i�-m�}&i��m��I�&i��m��I�fK�$m��M�6I�$m��M�6I�$m��m��I�&i��m��I�&i��m����6I�$m��M�6I�$m��M�6I�li��m��I�&i��m��I�&i��m��M�6I�$m��M�6I�$m��M�6[�&i��m��I�&i��m��I�&i�-m�}&i��m��I�&i��m��I�fK�$m��M�6I�$m��M�6I�$m��m��I�&i��m��I�&i��m����6I�$m��M�6I�$m��M�6I�li��m��I�&i��m��I�&i��m��M�6I�$m��M�6I�$m��M�6[�&i��m��I�&i��m��I�&i�-m�}&i��m��I�&i��m��I�fK�$m��M�6I�$m��M�6I�$m��m��I�&i��m��I�&i��m����6I�$m��M�6I�$m��M�6I�li��m��I�&i��m��I�&i��m��M�6I�$m��M�6I�$m��M�6[�&i��m��I�&i��m��I�&i�-m�}i[�m��E�i[�m��E�VK�"m��-ҶH�"m��-ҶH�"m��m��E�i[�m��E�i[�m���ҶH�"m��-ҶH�"m��-ҶH�ji[�m��E�i[�m��E�i[�m��-ҶH�"m��-ҶH�"m��-ҶZ�i[�m��E�i[�m��E�i[-m�}i[�m��E�i[�m��E�VK�"m��-ҶH�"m��-ҶH�"m��m��E�i[�m��E�i[�m���g�b[�m!���b[�m!���b[Ml�-Ķ�Bl�-Ķ�Bl��&���b[�m!���b[�m!���V�Bl�-Ķ�Bl�-Ķ�Bl��M��Bl�-Ķ�Bl�-Ķ�jb[�m!���b[�m!���b[�m5�-Ķ�Bl�-Ķ�Bl�-Ķ��b[�m!���b[�m!���b[Ml�-Ķ�Bl�-Ķ�Bl��&���b[�m!���b[�m!���V�Bl�-Ķ�Bl�-Ķ�Bl��m��¾Tط
�Za�+싅}�����˅}�����¾`�7��a�1�K�}���a�3싆}Ӱ��]þlط��a�7��p�7��a�9�K�}밯��þx�7���þ|ط��a�?��}�� �ğK�}��!�=ľ��7�*b�E�ˈ}��:b�G��}#��$��ľ�ط�Zb�K�����b[�m!���b[�m!���V�Bl�-Ķ�Bl�-Ķ�Bl��m!���b[�m!���b[�m!��Ķ�Bl�-Ķ�Bl�-Ķ�jb[δ-ҶH�"m��-ҶH�"m�����E�i[�m��E�i[�m��E�VK�"m��-ҶH�"m��-ҶH�"m��M��"m��-ҶH�"m��-ҶH�ji[�m��E�i[�m��E�i[�m��-ҶH�"m��-ҶH�"m��-ҶZ�i[�m��E�i[�m��E�i[-m��-ҶH�"m��-ҶH�"m�����E�i[�m��E�i[�m��E�VK�"m��-ҶH�"m��-ҶH�"m��M��"m��-ҶH�"m��-ҶH�ji[�m��E�i[�m��E�i[�m��-ҶH�"m��-ҶH�"m��-ҶZ�i[�m��E�i[�m��E�i[-m��-ҶH�"m��-ҶH�"m�����E�i[�m��E�i[�m��E�VK�"m��-ҶH�"m��-ҶH�"m��M��"m��-ҶH�"m��-ҶH�ji[�m��E�i[�m��E�i[�m��-ҶH�"m��-ҶH�"m��-ҶZ�i[�m��E�i[�m��E�i[-m��-ҶH�"m��-ҶH�"m�����E�i[�m��E�i[�m��E�VK�"m��-ҶH�"m��-ҶH�"m��M��"m��-ҶH�"m��-ҶH�ji[�m��E�i[�m��E�i[�m��-ҶH�"m��-ҶH�"m��-ҶZ�i[�m��E�i[�m��E�i[-m��-ҶH�"m��-ҶH�"m�����E�i[�m��E�i[�m��E�VK�"m��-ҶH�"m��-ҶH�"m��M��"m��-ҶH�"m��-ҶH�ji[�m��E�i[�m��E�i[�m��-ҶH�"m��-ҶH�"m��-ҶZ�i[�m��E�i[�m��E�i[-m��-ҶH�"m��-ҶH�"m�����E�i[�m��E�i[�m��E�VK�"m��-ҶH�"m��-ҶH�"m��M��&m��mҶI�&m��mҶI�niۤm��M�6iۤm��M�6iۤm��mҶI�&m��mҶI�&m��mҶ[�6iۤm��M�6iۤm��M�6i�-m��mҶI�&m��mҶI�&m��햶M�6iۤm��M�6iۤm��M�vK�&m��mҶI�&m��mҶI�&m��M��&m��mҶI�&m��mҶI�niۤm��M�6iۤm��M�6iۤm��mҶI�&m��mҶI�&m��mҶ[�6iۤm��M�6iۤm��M�6i�-m��mҶI�&m��mҶI�&m��햶M�6iۤm��M�6iۤm��M�vK�&m��mҶI�&m��mҶI�&m��M��&m��mҶI�&m��mҶI�niۤm��M�6iۤm��M�6iۤm��mҶI�&m��mҶI�&m��mҶ[�6iۤm��M�6iۤm��M�6i�-m��mҶI�&m��mҶI�&m��햶M�6iۤm��M�6iۤm��M�vK�&m��mҶI�&m��mҶI�&m��M��&m��mҶI�&m��mҶI�niۤm��M�6iۤm��M�6iۤm��mҶI�&m��mҶI�&m��mҶ[�6iۤm��M�6iۤm��M�6i�-m��mҶI�&m��mҶI�&m��햶M�6iۤm��M�6iۤm��M�vK�&m��mҶI�&m��mҶI�&m��m���S�zX��A`=	�G��,��a`=�ǁ�<���z$X��`=�g,X���`=�G��l����z<X����zDX��!a=%�Ǆ���֓�~F�������z\X��a=1�G��̰��a=5�ǆ�ܰ֓�ztX���a==�g|X��b=A�G���"�S�z�X��$F�6iۤm��M�6iۤm��M�vK�&m��mҶI�&m��mҶI�&m��m��M�6iۤm��M�6iۤm���ҶI�&m��mҶI�&m��mҶI�niۤm��M�6iۤm��M�6iۤm��mҶI�&m��mҶI�&m��mҶ[�6iۤm��M�6iۤm��M�6i�-m�}6iۤm��M�6iۤm��M�vK�&m��mҶI�&m��mҶI�&m��m��M�6iۤm��M�6iۤm���ҶI�&m��mҶI�&m��mҶI�niۤm��M�6iۤm��M�6iۤm��mҶI�&m��mҶI�&m��mҶ[�6iۤm��M�6iۤm��M�6i�-m�}6iۤm��M�6iۤm��M�vK�&m��mҶI�&m��mҶI�&m��m��M�6iۤm��M�6iۤm���ҶI�&m��mҶI�&m��mҶI�niۤm��M�6iۤm��M�6iۤm��mҶI�&m��mҶI�&m��mҶ[�6iۤm��M�6iۤm��M�6i�-m�}6iۤm��M�6iۤm��M�vK�&m��mҶI�&m��mҶI�&m��m��M�6iۤm��M�6iۤm���ҶI�&m��mҶI�&m��mҶI�niۤm��M�6iۤm��M�6iۤm��mҶI�&m��mҶI�&m��mҶ[�6iۤm��M�6iۤm��M�6i�-m�}6iۤm��M�6iۤm��M�vK�&m��mҶI�&m��mҶI�&m��m��M�6iۤm��M�6iۤm���ҶI�&m��mҶI�&m��mҶI�niۤm��M�6iۤm��M�6iۤm��mҶI�&m��mҶI�&m��mҶ[�6iۤm��M�6iۤm��M�6i�-m�}i;�퐶C�i;�퐶C�NK�!m���vH�!m���vH�!m��퐶C�i;�퐶C�i;�퐶��vH�!m���vH�!m���vH�ii;�퐶C�i;�퐶C�i;�����vH�!m���vH�!m���vZ�i;��    �C�i;�퐶C�i;-m�}i;�퐶C�i;�퐶C�NK�!m���vH�!m���vH�!m��퐶C�i;�퐶C�i;�퐶��vH�!m���vH�!m���vH�ii;�퐶C�i;�퐶C�i;�����vH�!m���vH�!m���vZ�i;�퐶C�i;�퐶C�i;-m�}i;�퐶C�i;�퐶C�NK�!m���vH�!m���vH�!m��퐶C�i;�퐶C�i;�퐶��vH�!m���vH�!m���vH�ii;�퐶C�i;�퐶C�i;�����vH�!m���vH�!m���vZ�i;�퐶C�i;�퐶C�i;-m�}i;�퐶C�i;�퐶C�NK�!m���vH�!m���vH�!m��퐶C�i;�퐶C�i;�퐶��vH�!m���vH�!m���vH�ii;�퐶C�i;�퐶C�i;�����vH�!m���vH�!m���vZ�i;�퐶C�i;�퐶C�i;-m�}i;�퐶C�i;�퐶C�NK�!m���vH�!m���vH�!m��퐶C�i;�퐶C�i;�퐶��vH�!m���vH�!m���vH�ii;�퐶C�i;�퐶C�i;�����vH�!m���vH�!m���vZ�i;�퐶C�i;�퐶C�i;-m�g�������c�{n����=��g������=����� ����#�{���)�?c�{���I�=ʿg��0������{���@����#�{����=ֿ���`����3ڿg��p������{���	�=�g����)�=��������ߣ�{���i�?��{����=�g�������c�{�����vH�!m���vH�!m���vZ�i;�퐶C�i;�퐶C�i;h���� ��p;�� ��p;�� �����*p;�� ��p;�� ��N��n��v��n��v��n��� ��p;�� ��p;�� ���v��n��v��n��v��ip�s��n��v��n��v�p;�� ��p;�� ��p;n��v��n��v��n����p;�� ��p;�� ��N��n��v��n��v��n��� ��p;�� ��p;�� ���v��n��v��n��v��ipS�s��n��v��n��v�p;�� ��p;�� ��p;n��v��n��v��n����p;�� ��p;�� ��N��n��v��n��v��n��� ��p;�� ��p;�� ���v��n��v��n��v��ipS��O}���n��v��n��7�'��~������~�O����/��v��n��v��n���C�i;�퐶C�i;�퐶��vH�!m���vH�!m���vH�ii;�퐶C�i;�퐶C�i;�����vH�!m���vH�!m���vZڤ�\�vI�%m��]�vI�%m��ݖ�K�.i��풶K�.i��풶K�nK�%m��]�vI�%m��]�vI�%m��풶K�.i��풶K�.i��풶��vI�%m��]�vI�%m��]�vI�mi��풶K�.i��풶K�.i�����]�vI�%m��]�vI�%m��]�v[��\�vI�%m��]�vI�%m��ݖ�K�.i��풶K�.i��풶K�nK�%m��]�vI�%m��]�vI�%m��풶K�.i��풶K�.i��풶��vI�%m��]�vI�%m��]�vI�mi��풶K�.i��풶K�.i�����]�vI�%m��]�vI�%m��]�v[��\�vI�%m��]�vI�%m��ݖ�K�.i��풶K�.i��풶K�nK�%m��]�vI�%m��]�vI�%m��풶K�.i��풶K�.i��풶��vI�%m��]�vI�%m��]�vI�mi��풶K�.i��풶K�.i�����]�vI�%m��]�vI�%m��]�v[��\�vI�%m��]�vI�%m��ݖ�K�.i��풶K�.i��풶K�nK�%m��]�vI�%m��]�vI�%m��풶K�.i��풶K�.i��풶��vI�%m��]�vI�%m��]�vI�mi��풶K�.i��풶K�.i�����]�vI�%m��]�vI�%m��]�v[��\�vI�%m��]�vI�%m��ݖ�K�.i��풶K�.i��풶K�nK�%m��]�vI�%m��]�vI�%m��풶K�.i��풶K�.i��풶��vI�%m��]�vI�%m��]�vI�mi��풶K�.i��풶K�.i�����]�vI�%m��]�vI�%m��]�v[��\�vI�%m��]�vI�%m��ݖ�K�.i��풶K�.i��풶K�nK�%m��]�vI�%m��]�vI�%m��풶K�.i��풶K�.i��풶��vI�%m��]�vI�%m��]�vI�mi��풶K�.i��풶K�.i�����]�vI�%m��]�vI�%m��]�v[�n��ُl�+���f���m�K���f�����f����m�{���f���On�����f�����f���o�˛��f��ُo����f���� g���Op���g����p�;��g����g��ُq�k��g���r����$g����(g����r����0g���Os�ۜ�8g����<g���t���Dg��ُt�+��Lg����P'b���"���.b���"���.b�Ml�]�v�El�]�v�El��&���.b���"���.b���"���n�El�]�v�El�]�v�El���"���.b���"���.b���"���v�El�]�v�El�]�v�mb���"���.b���"���.b���6�)����"���.b���"���.b�Ml�]�v�El�]�v�El��&���.b���"���.b���"���n�El�]�v�El�]�v�El���"���.b���"���.b���"���v�El�]�v�El�]�v�mb���"���.b���"���.b���6�)����"���.b���"���.b�Ml�]�v�El�]�v�El��&���.b���"���.b���"���n�u�풶K�.i��풶K�.i�����]�vI�%m��]�vI�%m��]�v[�.i��풶K�.i��풶K�.i�-m��]�vI�%m��]�vI�%m��ݖ6�>Aڂ�i��-H[�� m���-H[�� mAڂ�i��-Zڂ�i��-H[�� mAڂ�EK[�� mAڂ�i��-H[��hi��-H[�� mAڂ�i�-mAڂ�i��-H[�� mAڢ�-H[�� mAڂ�i��-H[��)�	��-H[�� mAڂ�i��� mAڂ�i��-H[�� m���-H[�� mAڂ�i��-Zڂ�i��-H[�� mAڂ�EK[�� mAڂ�i��-H[��hi��-H[�� mAڂ�i�-mAڂ�i��-H[�� mAڢ�M�O�� mAڂ�i��-H[��i��-H[�� mAڂ�i��� mAڂ�i��-H[�� m���-H[�� mAڂ�i��-Zڂ�i��-H[�� mAڂ�EK[�� mAڂ�i��-H[��hi��-H[�� mAڂ�i�-m�}��i��-H[�� mAڢ�-H[�� mAڂ�i��-H[��i��-H[�� mAڂ�i��� mAڂ�i��-H[�� m���-H[�� mAڂ�i��-Zڂ�i��-H[�� mAڂ�EK[�� mAڂ�i��-H[��hiS��-H[�� mAڂ�i�-mAڂ�i��-H[�� mAڢ�-H[�� mAڂ�i��-H[��i��-H[�� mAڂ�i��� mAڂ�i��-H[�� m���-H[�� mAڂ�i��-Zڂ�i��-H[�� mAڂ�EK�b� mAڂ�i��-H[��hi��-H[�� mAڂ�i�-mAڂ�i��-H[�� mAڢ�-H[�� mAڂ�i��-H[��i��-H[�� mAڂ�i��� mAڂ�i��-H[�� m���-H[�� mAڂ�i��-Z��i��-H[�� mAڂ�EK[�� mAڂ�i��-H[��hi��-H[�� mAڂ�i�-mAڂ� �  i��-H[�� mAڢ�-H[�� mAڂ�i��-H[��i��-H[�� mAڂ�i��� mAڂ�i��-H[�� m�Ҧ�'H[�� mAڂ�i��-Zڂ�i��-H[�� mAڂ�EK[�� mAڂ�i��-H[��hi��-H[�� mAڂ�i�-mAڂ�i��-H[�� mAڢ�-H[�� mAڂ�i��-H[��i��-H[�� mAڂ�i��6�>Aڂ�i��-H[�� m���-H[�� mAڂ�i��-Zڂ�i��-H[�� mAڂ�EK[�� mAڂ�i��-H[��hi��-H[�� mAڂ�i�-mAڂ�i��-H[�� mAڢ�-H[�� mAڂ�i��-H[��)�	��-H[�� mAڂ�i��� mAڂ�i��-H[�� m���-H[�� mAڂ�i��-Zڂ�i��-H[�� mAڂ�EK[�� mAڂ�i��-H[��hi��-H[�� mAڂ�i�-mAڂ�i��-H[�� mAڢ�M�O��$mIڒ�%iKҖ�-I[��%iKҖ�-I[��$mIڒ�%i˖�$mIڒ�%iKҖ�-I[��$m�Җ�-I[��$mIڒ�%iKҖ�-[ڒ�%iKҖ�-I[��$mIڒ�eK[��$mIڒ�%iKҖ�-I[��liKҖ�-I[��$mIڒ�%iKҖ-m�}��%iKҖ�-I[��$mIڲ�-I[��$mIڒ�%iKҖ�-I[��%iKҖ�-I[��$mIڒ�%i˖�$mIڒ�%iKҖ�-I[��$m�Җ�-I[��$mIڒ�%iKҖ�-[ڒ�%iKҖ�-I[��$mIڒ�eK[��$mIڒ�%iKҖ�-I[��liS쓤-I[��$mIڒ�%iKҖ-mIڒ�%iKҖ�-I[��$mIڲ�-I[��$mIڒ�%iKҖ�-I[��%iKҖ�-I[��$mIڒ�%i˖�$mIڒ�%iKҖ�-I[��$m�Җ�-I[��$mIڒ�%iKҖ�-[ڒ�%iKҖ�-I[��$mIڒ�eK�b�$mIڒ�%iKҖ�-I[��liKҖ�-I[��$mIڒ�%iKҖ-mIڒ�%iKҖ�-I[��$mIڲ�-I[��$mIڒ�%iKҖ�-I[��%iKҖ�-I[��$mIڒ�%i˖�$mIڒ�%iKҖ�-I[��$m�Җ�-I[��$mIڒ�%iKҖ�-[��$iKҖ�-I[��$mIڒ�eK[��$mIڒ�%iKҖ�-I[��liKҖ�-I[��$mIڒ�%iKҖ-mIڒ�%iKҖ�-I[��$mIڲ�-I[��$mIڒ�%iKҖ�-I[��%iKҖ�-I[��$mIڒ�%i˖�$mIڒ�%iKҖ�-I[��$m�Ҧ�'I[��$mIڒ�%iKҖ�-[ڒ�%iKҖ�-I[��$mIڒ�eK[��$mIڒ�%iKҖ�-I[��liKҖ�-I[��$mIڒ�%iKҖ-mIڒ�%iKҖ�-I[��$mIڲ�-I[��$mIڒ�%iKҖ�-I[��%iKҖ�-I[��$mIڒ�%i˖6�>Iڒ�%iKҖ�-I[��$m�Җ�-I[��$mIڒ�%iKҖ�-[ڒ�%iKҖ�-I[��$mIڒ�eK[��$mIڒ�%iKҖ�-I[��liKҖ�-I[��$mIڒ�%iKҖ-mIڒ�%iKҖ�-I[��$mIڲ�-I[��$mIڒ�%iKҖ�-I[��)�IҖ�-I[��$mIڒ�%i˖�$mIڒ�%iKҖ�-I[��$m�Җ�-I[��$mIڒ�%iKҖ�-[ڒ�%iKҖ�-I[��$mIڒ�eK[��$mIڒ�%iKҖ�-I[��liKҖ�-I[��$mIڒ�%iKҖ-mIڒ�%iKҖ�-I[��$mIڲ�M�O��$mIڒ�%iKҖ�-I[��%iKҖ�-I[��$mIڒ�%i˖�$mIڒ�%iKҖ�-I[��$m�Җ�-I[��$mIڒ�%iKҖ�-[ڒ�%iKҖ�-I[��$mIڒ�eK[��$mIڒ�%iKҖ�-I[��liKҖ�-I[��$mIڒ�%iKҖ-m�}��%iKҖ�-I[��$mIڲ�-I[��$mIڒ�%iKҖ�-I[��%iKҖ�-I[��$mIڒ�%i˖�$mIڒ�%iKҖ�-I[��$m�Җ�-I[��$mIڒ�%iKҖ�-[ڒ�%iKҖ�-I[��$mIڒ�eK[��$mIڒ�%iKҖ�-I[��liS�S��H[��"mEڊ�i+�V-mEڊ�i+�V��H[��"mEڪ��H[��"mEڊ�i+�V��H[��i+�V��H[��"mEڊ�i���"mEڊ�i+�V��H[��"m��V��H[��"mEڊ�i+�V��Zڊ�i+�V��H[��"mEڊ�UK�b�"mEڊ�i+�V��H[��Bcջ�n�
�p+�V���[�n����*p+�V���[�n�
�U�[�n�
�p+�V���[�jp+�V���[�n�
�p+�Vn�
�p+�V���[�nܪ���[�n�
�p+�V���[5�p+�V���[�n�
�p��n�
�p+�V���[�n��V���[�n�
�p+�V���
�p+�V���[�n�
�U�[�n�
�p+�V���[�jp+�V���[�n�
�p+�Vn�
�p+�V���[�nܪ�M�O�n�
�p+�V���[5�p+�V���[�n�
�p��n�
�p+�V���[�n��V���[�n�
�p+�V���
�p+�V���[�n�
�U�[�n�
�p+�V���[�jp+�V���[�n�
�p+�Vn���y�Y~�����	?����t�V���[�n�
�p+�VnEڊ�i+�V��H[��"m��V��H[��"mEڊ�i+�V��Zڊ�i+�V��H[��"mEڊ�UK[��"mEڊ�i+�V��H[��ji+�V��H[��"mEڊ�i+�V-mR}��i+�V��H[��"mEڪ��H[��"mEڊ�i+�V��H[��i+�V��H[��"mEڊ�i���"mEڊ�i+�V��H[��"m��V��H[��"mEڊ�i+�V��Zڊ�i+�V��H[��"mEڊ�UK[��"mEڊ�i+�V��H[��jiS�S��H[��"mEڊ�i+�V-mEڊ�i+�V��H[��"mEڪ��H[��"mEڊ�i+�V��H[��i+�V��H[��"mEڊ�i���"mEڊ�i+�V��H[��"m��V��H[��"mEڊ�i+�V��Zڊ�i+�V��H[��"mEڊ�UK�b�"mEڊ�i+�V��H[��ji+�V��H[��"mEڊ�i+�V-mEڊ�i+�V��H[��"mEڪ��H[��"mEڊ�i+�V��H[��i+�V��H[��"mEڊ�i���"mEڊ�i+�V��H[��"m��V��H[��"mEڊ�i+�V��Z��i+�V��H[��"mEڊ�UK[��"mEڊ�i+�V��H[��ji+�V��H[��"mEڊ�i+�V-mEڊ�i+�V��H[��"mEڪ��H[��"mEڊ�i+�V��H[��i+�V��H[��"mEڊ�i���"mEڊ�i+�V��H[��"m�ҦاH[��"mEڊ�i+�V��Zڊ�i+�V��H[��"mEڊ�UK[��"mEڊ�i+�V��H[��ji+�V��H[��"mEڊ�i+�V-mEڊ�i+�V��H[��"mEڪ��H[��"mEڊ�i+�V��H[��i+�V��H[��"mEڊ�i��6���7��w����ӿ����������^�^�^�^�^�^�^�^�^���������������������Y�W/���������z�_��^�W/��g�?���^�O/��������z�?�������{�����^�o/����������������{�߽��^�w/������Y�������?��]�Q      C	      x������ � �      '	   
  x�=��N�0E��+��9��8q�E����5�`al���7��	�1��0��{߻���������<V�9�JЎ��0O^��IX&�[*[Rg�l�8��]ѿ�B⢡5#��Ja�����V�C�����UP77Tr"������wg��"�UPv�d��������$�=�BR�I�'x�p�u<٢���R,Z([A�������[��z\��:>%�n������;*�~d(HA8�Γ�hag�ύ��c<�=ٚ�RB~�*fb      =	   M	  x�m�Ko��ϭ����&-eXذ .t�%����3Ɛ�������U=܅c��U���m��^��8|�<\��_���q>~�1�brQ!o\������7��p:��0�:�����fSI��P|�>)����۱���7~x��x>�χ�q�-9%���؜LQo�7��\ڱ0|���O����r0Κ�7��M))���k����4w��^g��1�;�����(�߻K��/Oχ/�yɖk��G�t��c"�b�m�dn�~?~�|���J�&9Wt*��#E!t�JK�m<s�����K.>��9��s�*���������z�y���r����UI���l���=�������Á���)�����P�85��>��������e�c�X�N:åst
R��d�閻~:�/��Pm
4�����D@Y� m�L���T��8_r6�f���0|/����r�kC4�r�j��VE����>�y��A�B�΄�"'��@���y�����x��N�h�)�6�<P� ~ߗ'��z�9�k<�ǤT��&vH6g�W�]wTIɾ\����r����P�D#�5d����"�i]�j;�ih(�����A�Nn�uf��O�G���pd��50����.�\��;� ���Ǯ��1QT�3��70&��]N7�/瞫fc]�� �����U�I���s�%�Cq�`t����
��6�C���%r
���6�Bm��8��B<<1� B���ED�x��i���<&��G]�˥K]��=2�`�H��IC����F3�½����aO�o-Y:	�D���)9&%(ⶓ�w�n���l�o��Ȑ2�D�]vǼ��}�s:�(gKZ�_&`wM�I�6!�ɀN����S�	�j
iߑ�����r=��T~ꟕ�e��!��{���W��Pc��Vn�I��Q�0|O���<v��{U��je�@]�;g�/���9�f	��fR��ѝJ+7�|'�4)4��X���P�_�3��Q+���řțn����MC"OJ>MU-Հ�[i��u4�.M�)��r(�����pݚa4�y�bГ�[R3�������$EO� �������Ͻ�pX�iL�e�V�\`���bn� ������!X�e�)g*�J�]i	C�Ŵ��j0QJ*|#�i	q����Ԓ1�7�)%�L���=0u��nd�X�[e1�������e�6aN����f��G�e J��W��i�C��Ԍ�LHp?�yT�M�:�m|�
:����RYE�E K���BY*�����
l�>���T�w���o6Ӛ��y<��kg]��6b��T���l\����=�q��,s�<�Vٞ��b4��S���q�!���@G�h���*�î�1��`}+ ���Ъ�`��^�5�uZp�9`c޼0Q2���y=��l�*w�H3u����G�I3<�Ib���@d-����:#)n#�bEC@(�)ݟ�eZy����T-M19d�xE�(�ib6�\�rR��Dj�����_O�������==����v�?"R ����a�%}����1���yPa[Χ�����"
�j�YQ��9�=��8��
����4��C-�F��*��'�\}"[��{�\llv]���8/][/*TIu�m[WѰ�����T�6����R�6"}��DH���\��E�%^���X2��t��l�d��[�]�W�G庬[�n���D/�@��:���	Fr�M�^�i�X��b�Y�\!��a�b��7-��<�����*U��|�ok�`ޞ6�ds�b�'"{xg�L�-���3ʗ�P�l�x��2|[@;17�I��X�)���=����RQ��!!=�exˋ���.����@�;LA�dn�j��xka��9���婭8�jɶ�r��2-�{4�� k%K�i�o"#�*�c���M��/��Bn���^���d�Io�_��"�e���Eח�ɹX���,�5��y[Ҳ�f���7��4�X�lu���S��%'n�	�*�ia6�`Z%o���jh�SF�S>��#f��8�9�ʰ��{�?��FQJ�U�,_0Va̞�UfV��a�߷oe�.��g�i���ڀ��>9�y{�bϛ�m_�04��.8�[�~|a�yU^�m`��⨛&�q��J�򎅧���Y��ҳ�H/��/|3BwW]�i�a�fxq/�Q%IRȭװ����=�f�BƁ�05��ީ���@��<�j��9A˒�>���b5p���V�
�a���X�k�tQ��)F]�ؓ�;�jwѲ��r2,r4Mؤ������<��c����gY�E�{�=�i��ns�쁬�Z<I�@��a<�b��`�	�f[�����/����͛7��3>      9	      x������ � �      D	      x������ � �      A	      x������ � �      *	   |  x�]T�n�0>+O�'D���c�l][v�E]�΃�N3l{�1��؁�H��#A���!����?�n;�NS�g��Y4p�6���:�}�(�I�K��C�ѧ���V"G����@|k��Ө-;�R�����7@�Z�dpn`��`�����]��{�����u1b��>RX���F��C@0_g���}ߍ�S:�G�(�)�����ʫ��גsm��	=F��R��������Ӥ�fU���z�I�d��8㢳�ŵ�7
��N`�Qt8���_��?�)4w�P{��T�J�}���ς��={,�DZ�Xp��YA� d|ţ��7s��F�] �=T%	���d4��ZϚ�� f$k�g0V��3���1��A�z���ԅ��-���n���Xt�!M_��>���*i �� V"J�4��.�tU�`тn�֑�O�i�8{!�$W(^��V^�� P.�@qB������ʴ��"�L�z��{�%�w�*t>%I������o�Wa1^����6B}ا�\�Ki���Z�.i_+Q=��՗�QQ�9:�}��k)8�*��b�4����K���X�X\�xэi��d\\�@u����#b�#	Z<#
,�=��/�6���[x�      7	      x������ � �      6	      x������ � �      ?	       x�3�44200�420��50�5������� 2��      /	   (  x�M�I�d!C����`��K��mI�蝊��W6dv߿���;~:����<�_��L��S��O�� ����~c|���������2�`����X3��k�vR/���z�kmG�Hi}�ec�͌60�F�><sf3�ϵ����N�J������H�"�cCum��F��u�,S���&�l��8D�'�E*�Hm�C�>&3�!�P�!��q�������K� ݖR�AI�pHa�a1sv�b�Ag�,��J�L��8ӄ�o��E��/R��L��Ϊ��F��a)н���@��'��7T+V��f�4a��.V_��ՙD�m�NTWDu4P��o��z��P��P���P���`g�\��`W��[�-�_됵�}A6&4a7�5X�]����Hּ�벚�X{��e[uS���4�Ne�FB�.:�ِP��/fn�~��s`.KY��Z_�փ�j)�\^�$e�t[����0�(Y�rP��YD�3�w���\p�i�߹�=����㹘���9`�O�z�#9́�Z]zH����vW�H:�kw{w�ԃ�.��R��J��Z��Uks�eM�����]�Ժ��
���i�b��-U$Pi��:�iq�5��ʈәP��4uG'�w��4p~ލ�z����f����B<��s���}��.i�Ś#�wI{���2T��P���͉ŝ�A�Fv�昢`
��K)�	s�6���{_�����!��+~�\���)Z�hw�[������d"0~{���*y� ���:v|@h�ֈ�ڪwؕ���{sUPS�	����}� Z�|�      1	      x������ � �      .	   J  x�m�An� E����m�mUu�V��t�����8Rs���D
N#e�b�g��#�b���%��٤@�h����*)Yא'�r;��޳��
? ���jF�?������h/�\A��!�Ѧ�dH������+"�_$e�9y8=2�������/��pJ��VtWT�(D�j�(>�.)�4�AKA����f�aA�u�<�C"+�v���R�>��G�G4�V���^���ֹ�nچ��t0�إ-&��:L!fse����nG��z��&�&8�c�dK�e�����L%[�?h�v]^�����{4��:Uo7UU��"Ų      3	   �   x�%һm@1��Z&x�ۻd�9B*�!V>�mѢe��1�3&w������{�r_<n���ѳ��"A4�Q!2={1Tfd�!2D��"Cd���Q��9d��!2D��"3sCeVN"Cd��!2D�Ȭ\�P��K��"Cd��!2;w1T��!2D��"Cd���S��yd��!2D��"s�Ce^^"Cd��!2D�ȼ|�P����њ��њ�Қ �Қ�������Of�=_�      )	   &   x�3�tL����,.)JL�/�2���/�M������ ��      ;	   �   x�u�A
1E��)�Н�T�x ���5J`���rn��AP�������$��aV�K��$���3����PLV��=�S�����SRc���"y^by��p���f OD�ŉ�;��p��!�_��)з��s/9CV      ,	   -   x�3�tL����,.)JL�/�2�.-H-�-.M,������� �=�      -	   �   x�5�;NE1��^��g��5HPP���3�C�'�L��iHsyy�x���Q�K�'{�\�$�$WxN 2�L-R(��}&�E)��CR��*ﹳ��Q)f��j�Ԃ:�Z�v�<.9�1���K�=��#�8h8eݎ�����fy�\5u��p��X�U֌C~~�޾��B��<�����A�     