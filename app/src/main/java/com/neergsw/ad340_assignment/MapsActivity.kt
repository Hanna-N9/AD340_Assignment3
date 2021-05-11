package com.neergsw.ad340_assignment

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.neergsw.ad340_assignment.databinding.ActivityMapsBinding
import android.location.Geocoder
import com.google.android.gms.maps.model.BitmapDescriptorFactory.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }


    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLng)
                mMap.addMarker(MarkerOptions().position(currentLatLng).icon(defaultMarker(HUE_MAGENTA)))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 13f))
            }
        }
    }


    private fun placeMarkerOnMap(location: LatLng) {
        val markerOptions = MarkerOptions().position(location)
        val titlesr = getAddress(location)
        markerOptions.title(titlesr)
        mMap.addMarker(markerOptions)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        setUpMap()


//Point 1
        val cloverdale = LatLng(47.52678298950195, -122.39273071289062)
        mMap.addMarker(
            MarkerOptions().position(cloverdale).title("Fauntleroy Way SW & SW Cloverdale St")
        )

//Point 2
        val alaska1 = LatLng(47.5611635, -122.3850949)
        mMap.addMarker(MarkerOptions().position(alaska1).title("42nd Ave SW & SW Alaska St"))

        val alaska2 = LatLng(47.56110763549805, -122.38677978515625)
        mMap.addMarker(MarkerOptions().position(alaska2).title("California Ave SW & SW Alaska St"))

//Point 3
        val hanford = LatLng(47.57563400268555, -122.38675689697266)
        mMap.addMarker(MarkerOptions().position(hanford).title("California Ave SW & SW Hanford St"))

//Point 4
        val admiral1 = LatLng(47.581172943115234, -122.38388061523438)
        mMap.addMarker(MarkerOptions().position(admiral1).title("41st Ave SW & SW Admiral Way"))

        val admiral2 = LatLng(47.58119583129883, -122.38654327392578)
        mMap.addMarker(MarkerOptions().position(admiral2).title("California Ave SW & SW Alaska St"))

//Point 5
        val fauntleroy = LatLng(47.56105041503906, -122.38148498535156)
        mMap.addMarker(
            MarkerOptions().position(fauntleroy).title("Fauntleroy Way SW & SW Alaska St")
        )

//Point 6
        val barton = LatLng(47.52107238769531, -122.37676239013672)
        mMap.addMarker(MarkerOptions().position(barton).title("35th Ave SW & SW Barton St"))

        val roxbury = LatLng(47.517486572265625, -122.37687683105469)
        mMap.addMarker(MarkerOptions().position(roxbury).title("35th Ave SW & SW Roxbury St"))

//Point 7
        val n85 = LatLng(47.690608978271484, -122.37680053710938)
        mMap.addMarker(MarkerOptions().position(n85).title("15th Ave NW & NW 85th St"))

//Point 8
        val n651 = LatLng(47.67598342895508, -122.37612915039062)
        mMap.addMarker(MarkerOptions().position(n651).title("15th Ave NW & NW 65th St NS"))

        val n652 = LatLng(47.6761414, -122.376817)
        mMap.addMarker(MarkerOptions().position(n652).title("15th Ave NW & NW 65th St EW"))

//Point 9
        val holden = LatLng(47.53336715698242, -122.37662506103516)
        mMap.addMarker(MarkerOptions().position(holden).title("15th Ave NW & NW 85th St"))

//Point 10
        val morgan = LatLng(47.5446891784668, -122.37646484375)
        mMap.addMarker(MarkerOptions().position(morgan).title("35th Ave SW & SW Holden St"))

//Point 11
        val dravus = LatLng(47.64847183227539, -122.37612915039062)
        mMap.addMarker(MarkerOptions().position(dravus).title("15th Ave W & W Dravus St"))

//Point 12
        val emerson = LatLng(47.65304946899414, -122.37613677978516)
        mMap.addMarker(MarkerOptions().position(emerson).title("15th Ave W & W Emerson St"))

//Point 13
        val market1 = LatLng(47.668670654296875, -122.37626647949219)
        mMap.addMarker(MarkerOptions().position(market1).title("15th Ave NW & NW Market St EW"))

        val market2 = LatLng(47.6688871, -122.376398)
        mMap.addMarker(MarkerOptions().position(market2).title("15th Ave NW & NW Market St NS"))

//Point 14
        val faunt = LatLng(47.5234171, -122.3930203)
        mMap.addMarker(MarkerOptions().position(faunt).title("35th Ave SW @ Fauntleroy Way SW"))

//Point 15
        val armory = LatLng(47.637752532958984, -122.37619018554688)
        mMap.addMarker(MarkerOptions().position(armory).title("15th Ave NW & NW Leary Way"))

//Point 16
        val leary = LatLng(47.663665771484375, -122.37606048583984)
        mMap.addMarker(MarkerOptions().position(leary).title("15th Ave W & W Armory Way"))

//Point 17
        val charlestown = LatLng(47.570118, -122.358224)
        mMap.addMarker(MarkerOptions().position(charlestown).title("W Seattle Bridge near SW Charlestown St")
        )

        val harbor = LatLng(47.571672, -122.327559)
        mMap.addMarker(MarkerOptions().position(harbor).title("Harbor Ave SW & S Spokane St"))

//Point 18
        val elliott = LatLng(47.6264103, -122.367838)
        mMap.addMarker(MarkerOptions().position(elliott).title("Elliott Ave W & W Mercer Pl"))

//Point 19
        val leary2 = LatLng(47.6586951, -122.364344)
        mMap.addMarker(MarkerOptions().position(leary2).title("Leary Way NW & NW 43rd St"))

//Point 20
        val mercer = LatLng(47.624556, -122.346268)
        mMap.addMarker(MarkerOptions().position(mercer).title("Queen Anne Ave N & Mercer St"))

        val elliott2 = LatLng(47.617031, -122.354691)
        mMap.addMarker(MarkerOptions().position(elliott2).title("Western Ave & Elliott St"))

//Point 21
        val chelan = LatLng(47.572095, -122.360108)
        mMap.addMarker(MarkerOptions().position(chelan).title("Chelan Ave SW & W Marginal Way"))

        val delridge = LatLng(47.589385, -122.390323)
        mMap.addMarker(
            MarkerOptions().position(delridge).title("W Seattle Bridge @ Delridge Way Exit")
        )

//Point 22
        val roy = LatLng(47.6312384, -122.356615)
        mMap.addMarker(MarkerOptions().position(roy).title("Queen Anne Ave N & Roy St"))

//Point 23
        val greenwood = LatLng(47.572095, -122.360108)
        mMap.addMarker(MarkerOptions().position(greenwood).title("Greenwood Ave N & N 125th St"))

        val greenwood2 = LatLng(47.7229793, -122.355713)
        mMap.addMarker(
            MarkerOptions().position(greenwood2).title("Greenwood Ave N & N 130th St")
        )

//Point 24
        val greenwood3 = LatLng(47.7141587837849, -122.355515915087)
        mMap.addMarker(MarkerOptions().position(greenwood3).title("Greenwood Ave N & N 117th St"))

//Point 25
        val broad = LatLng(47.618579, -122.350357)
        mMap.addMarker(MarkerOptions().position(broad).title("Elliott Ave & Broad St"))

        val denny1 = LatLng(47.618524, -122.356575)
        mMap.addMarker(
            MarkerOptions().position(denny1).title("1st Ave & Denny Way"))

        val broad2 = LatLng(47.618579, -122.350357)
        mMap.addMarker(MarkerOptions().position(broad2).title("Western Ave & Broad St"))

        val broad3 = LatLng(47.6171882, -122.3527772)
        mMap.addMarker(
            MarkerOptions().position(broad3).title("1st Ave & Broad St"))

//Point 26
        val greenwood4 = LatLng(47.7050835418972, -122.355452745492)
        mMap.addMarker(MarkerOptions().position(greenwood4).title("Greenwood Ave N & N 105th St"))

//Point 27
        val roxbury3 = LatLng(47.5174496, -122.3734677)
        mMap.addMarker(MarkerOptions().position(roxbury3).title("16th Ave SW & SW Roxbury St"))

        val roxbury4 = LatLng(47.491993, -122.353189)
        mMap.addMarker(
            MarkerOptions().position(roxbury4).title("15th Ave SW & SW Roxbury St"))

//Point 28
        val evanston = LatLng(47.651464, -122.351361)
        mMap.addMarker(MarkerOptions().position(evanston).title("Evanston Ave N & N 36th St"))

        val fremont = LatLng(47.651464, -122.351361)
        mMap.addMarker(
            MarkerOptions().position(fremont).title("Fremont Ave N & N 36th St"))

        val fremont2 = LatLng(47.649574279785156, -122.34973907470703)
        mMap.addMarker(MarkerOptions().position(fremont2).title("Fremont Ave N & N 34th St"))

//Point 29
        val wall = LatLng(47.61802, -122.344453)
        mMap.addMarker(MarkerOptions().position(wall).title("Western Ave & Wall St"))

        val wall1 = LatLng(47.6135196, -122.3504298)
        mMap.addMarker(
            MarkerOptions().position(wall1).title("3rd Ave & Wall St"))

        val wall2 = LatLng(47.602554, -122.334283)
        mMap.addMarker(MarkerOptions().position(wall2).title("1st Ave & Wall St EW"))

        val wall3 = LatLng(47.61480712890625, -122.34695434570312)
        mMap.addMarker(MarkerOptions().position(wall3).title("2nd Ave & Battery St"))

        val wall4 = LatLng(47.60621, -122.33207)
        mMap.addMarker(
            MarkerOptions().position(wall4).title("1st Ave & Wall St NS"))

        val wall5 = LatLng(47.612796783447266, -122.35114288330078)
        mMap.addMarker(MarkerOptions().position(wall5).title("Alaskan Way & Wall St"))

//Point 30
        val dexter = LatLng(47.6443011, -122.3467398)
        mMap.addMarker(MarkerOptions().position(dexter).title("4th Ave N & Dexter Ave N"))

        val raye = LatLng(47.642939, -122.376217)
        mMap.addMarker(
            MarkerOptions().position(raye).title("SR-99 @ Raye St"))

//Point 31
        val dexter1 = LatLng(47.589298, -122.384975)
        mMap.addMarker(MarkerOptions().position(dexter1).title("W Seattle Bridge Mid-Span"))

        val raye1 = LatLng(47.5715312, -122.3618849)
        mMap.addMarker(
            MarkerOptions().position(raye1).title("11th Ave SW & SW Spokane St"))

//Point 32
        val battery = LatLng(47.605837, -122.331148)
        mMap.addMarker(MarkerOptions().position(battery).title("5th Ave & Battery St East Side"))

        val battery1 = LatLng(47.605837, -122.331148)
        mMap.addMarker(
            MarkerOptions().position(battery1).title("5th Ave & Battery St West Side"))

        val battery2 = LatLng(47.6213047, -122.3474846)
        mMap.addMarker(MarkerOptions().position(battery2).title("5th Ave N & Broad St"))

        val battery3 = LatLng(47.61613464355469, -122.34523010253906)
        mMap.addMarker(MarkerOptions().position(battery3).title("4th Ave & Battery St"))

        val battery4 = LatLng(47.632304, -122.349574)
        mMap.addMarker(MarkerOptions().position(battery4).title("4th Ave N & Denny Way"))

        val battery5 = LatLng(47.6135362, -122.3504082)
        mMap.addMarker(MarkerOptions().position(battery5).title("6th Ave & Wall St"))

        val battery6 = LatLng(47.622021, -122.355608)
        mMap.addMarker(MarkerOptions().position(battery6).title("SR-99 @ Harrson St"))

//Point 33
        val west = LatLng(47.605837, -122.331148)
        mMap.addMarker(MarkerOptions().position(west).title("5th Ave & Mercer St EW"))

        val west1 = LatLng(47.629739, -122.347531)
        mMap.addMarker(MarkerOptions().position(west1).title("5th Ave N & Mercer St NS"))

        val west2 = LatLng(47.6418721, -122.3454647)
        mMap.addMarker(MarkerOptions().position(west2).title("6th Ave N & SR 99"))

        val west3 = LatLng(47.62319, -122.355598)
        mMap.addMarker(MarkerOptions().position(west3).title("SR-99 @ Republican St"))

        val west4 = LatLng(47.62801742553711, -122.34363555908203)
        mMap.addMarker(MarkerOptions().position(west4).title("Aurora Ave N & Ward St"))

        val west5 = LatLng(47.6220588684082, -122.34361267089844)
        mMap.addMarker(MarkerOptions().position(west5).title("Aurora Ave N & Harrison St"))

        val west6 = LatLng(47.6224765, -122.3445612)
        mMap.addMarker(MarkerOptions().position(west6).title("SR-99 @ Valley St"))

        val west7 = LatLng(47.62446212768555, 122.34625244140625)
        mMap.addMarker(MarkerOptions().position(west7).title("Taylor Ave & Mercer St"))

//Point 34
        val aurora = LatLng(47.6621454421964, -122.347149389976)
        mMap.addMarker(MarkerOptions().position(aurora).title("Aurora Ave N & N 46th St"))

//Point 35
        val highland = LatLng(47.5340778469943, -122.347070128431)
        mMap.addMarker(MarkerOptions().position(highland).title("Highland Park Way SW & SW Holden St"))

//Point 36
        val aurora2 = LatLng(47.7229682, -122.3451496)
        mMap.addMarker(MarkerOptions().position(aurora2).title("Aurora Ave N & N 130th St NS"))

        val aurora3 = LatLng(47.664138, -122.398893)
        mMap.addMarker(MarkerOptions().position(aurora3).title("Aurora Ave N & N 130th St EW"))

//Point 37
        val aurora4 = LatLng(47.694183349609375, -122.34463500976562)
        mMap.addMarker(MarkerOptions().position(aurora4).title("Aurora Ave N & N 90th St"))

        val aurora5 = LatLng(47.692412, -122.353951)
        mMap.addMarker(MarkerOptions().position(aurora5).title("Aurora Ave N & N 87th St NS"))

        val aurora6 = LatLng(47.6909437, -122.3443651)
        mMap.addMarker(MarkerOptions().position(aurora6).title("Aurora Ave N & N 85th St EW"))

//Point 38
        val aurora7 = LatLng(47.7335783, -122.3452798)
        mMap.addMarker(MarkerOptions().position(aurora7).title("Aurora Ave N & N 145th St NS"))

        val aurora8 = LatLng(47.7334976926491, -122.345090029483)
        mMap.addMarker(MarkerOptions().position(aurora8).title("Aurora Ave N & N 145th St EW"))

//Point 39
        val aurora9 = LatLng(47.7147767546112, -122.34485254875)
        mMap.addMarker(MarkerOptions().position(aurora9).title("Aurora Ave N & N 117th Pl"))

//Point 40
        val aurora10 = LatLng(47.70323181152344, -122.34467315673828)
        mMap.addMarker(MarkerOptions().position(aurora10).title("Aurora Ave N & N 103rd St"))

        val aurora11 = LatLng(47.7050512140763, -122.34469412927)
        mMap.addMarker(MarkerOptions().position(aurora11).title("Aurora Ave N & N 105th St EW"))

//Point 41
        val aurora12 = LatLng(47.6827723734034, -122.34448441424)
        mMap.addMarker(MarkerOptions().position(aurora12).title("Aurora Ave N & N Winona St"))

//Point 42
        val lo1 = LatLng(47.6104485, -122.3432553)
        mMap.addMarker(MarkerOptions().position(lo1).title("4th Ave & Virginia St"))

        val lo2 = LatLng(47.6113399, -122.3447182)
        mMap.addMarker(MarkerOptions().position(lo2).title("6th Ave & Lenora St"))

        val lo3 = LatLng(47.61027908325195, -122.34133911132812)
        mMap.addMarker(MarkerOptions().position(lo3).title("1st Ave & Stewart St"))

        val lo4 = LatLng(47.6105822, -122.3402531)
        mMap.addMarker(MarkerOptions().position(lo4).title("2nd Ave & Stewart St"))

        val lo5 = LatLng(47.61164093017578, -122.339599609375)
        mMap.addMarker(MarkerOptions().position(lo5).title("3rd Ave & Stewart st"))

        val lo6 = LatLng(47.61326599121094, -122.34434509277344)
        mMap.addMarker(MarkerOptions().position(lo6).title("2nd Ave & Blanchard St"))

//Point 43
        val lo7 = LatLng(47.6305863732788, -122.343520715635)
        mMap.addMarker(MarkerOptions().position(lo7).title("SR-99 @ Comstock St"))

//Point 44
        val lo8 = LatLng(47.6357534454735, -122.343482563802)
        mMap.addMarker(MarkerOptions().position(lo8).title("Aurora Ave N & Howe St"))

//Point 45
        val lo9 = LatLng(47.54027557373047, -122.34217834472656)
        mMap.addMarker(MarkerOptions().position(lo9).title("W Marginal Way SW & Highland Park Way SW"))

        val lo10 = LatLng(47.577585, -122.367603)
        mMap.addMarker(MarkerOptions().position(lo10).title("W Marginal Way SW @ Duwamish Trail"))

//Point 46
        val lo11 = LatLng(47.606919, -122.329093)
        mMap.addMarker(MarkerOptions().position(lo11).title("7th Ave & Virginia St"))

        val lo12 = LatLng(47.6182246, -122.3425847)
        mMap.addMarker(MarkerOptions().position(lo12).title("Dexter Ave & Denny Way"))

        val lo13 = LatLng(47.62088394165039, -122.33976745605469)
        mMap.addMarker(MarkerOptions().position(lo13).title("9th Ave N & Thomas St"))

        val lo14 = LatLng(47.616361, -122.338061)
        mMap.addMarker(MarkerOptions().position(lo14).title("Westlake Ave & Denny Way EW"))

        val lo15 = LatLng(47.616361, -122.338061)
        mMap.addMarker(MarkerOptions().position(lo15).title("Westlake Ave & Denny Way NS"))

        val lo16 = LatLng(47.622039794921875, -122.33843231201172)
        mMap.addMarker(MarkerOptions().position(lo16).title("Westlake Ave N & Harrison St"))

//Point 47
        val lo17 = LatLng(47.627168, -122.339624)
        mMap.addMarker(MarkerOptions().position(lo17).title("9th Ave N & Mercer St"))

        val lo18 = LatLng(47.625728607177734, -122.3386001586914)
        mMap.addMarker(MarkerOptions().position(lo18).title("Westlake Ave & Valley St"))

        val lo19 = LatLng(47.623886, -122.3397502)
        mMap.addMarker(MarkerOptions().position(lo19).title("9th Ave N & Roy St"))

        val lo20 = LatLng(47.6252748, -122.3421724)
        mMap.addMarker(MarkerOptions().position(lo20).title("Dexter Ave N & Mercer St"))

//Point 48
        val lo21 = LatLng(47.6117995, -122.3364219)
        mMap.addMarker(MarkerOptions().position(lo21).title("5th Ave & Pine St EW"))

        val lo22 = LatLng(47.606117248535156, -122.33753204345703)
        mMap.addMarker(MarkerOptions().position(lo22).title("1st Ave & Seneca St"))

        val lo23 = LatLng(47.60783386230469, -122.33910369873047)
        mMap.addMarker(MarkerOptions().position(lo23).title("1st Ave & Union St"))

        val lo24 = LatLng(47.60729217529297, -122.33710479736328)
        mMap.addMarker(MarkerOptions().position(lo24).title("2nd Ave & University St"))

        val lo25 = LatLng(47.604485, -122.339092)
        mMap.addMarker(MarkerOptions().position(lo25).title("Alaskan Way & Pike St"))

        val lo26 = LatLng(47.6121711730957, -122.33853149414062)
        mMap.addMarker(MarkerOptions().position(lo26).title("4th Ave & Olive St"))

        val lo30 = LatLng(47.604942321777344, -122.33800506591797)
        mMap.addMarker(MarkerOptions().position(lo30).title("Western Ave & Spring St"))

        val lo31 = LatLng(47.611209869384766, -122.33761596679688)
        mMap.addMarker(MarkerOptions().position(lo31).title("4th Ave & Pine St"))

        val lo32 = LatLng(47.605873107910156, -122.3403549194336)
        mMap.addMarker(MarkerOptions().position(lo32).title("Alaskan Way & University St"))

        val lo33 = LatLng(47.603782653808594, -122.33843994140625)
        mMap.addMarker(MarkerOptions().position(lo33).title("Alaskan Way & Madison St"))

        val lo34 = LatLng(47.6086663, -122.3384903)
        mMap.addMarker(MarkerOptions().position(lo34).title("2nd Ave & Pike St NS"))

        val lo35 = LatLng(47.6086663, -122.3384903)
        mMap.addMarker(MarkerOptions().position(lo35).title("2nd Ave & Pike St EW"))

        val lo36 = LatLng(47.6087532043457, -122.33688354492188)
        mMap.addMarker(MarkerOptions().position(lo36).title("3rd Ave & Union St"))

//Point 49
        val lo37 = LatLng(47.665034446401, -122.340207471558)
        mMap.addMarker(MarkerOptions().position(lo37).title("Greenlake Way N & N 50th St"))

//Point 50
        val lo38 = LatLng(47.575579772593, -122.339990401689)
        mMap.addMarker(MarkerOptions().position(lo38).title("SR-99 @ S Lander St"))

        val lo39 = LatLng(47.575451, -122.292994)
        mMap.addMarker(MarkerOptions().position(lo39).title("E Marginal Way S & S Hanford St"))

        val lo40 = LatLng(47.571672, -122.327559)
        mMap.addMarker(MarkerOptions().position(lo40).title("E Marginal Way S & S Spokane St"))

        val lo41 = LatLng(47.575579772593, -122.339990401689)
        mMap.addMarker(MarkerOptions().position(lo41).title("SR-99 @ S Lander St"))

//Point 51
        val lo42 = LatLng(47.5838592083257, -122.339552447146)
        mMap.addMarker(MarkerOptions().position(lo42).title("SR-99 @ S Walker St"))

//Point 52
        val lo43 = LatLng(47.5644997971235, -122.339439176042)
        mMap.addMarker(MarkerOptions().position(lo43).title("E Marginal Way S & S Idaho St"))

//Point 53
        val lo44 = LatLng(47.5574125251765, -122.339110501153)
        mMap.addMarker(MarkerOptions().position(lo44).title("E Marginal Way S @ Hudson St"))

        val lo45 = LatLng(47.557411193847656, -122.3342056274414)
        mMap.addMarker(MarkerOptions().position(lo45).title("1st Ave S & S Hudson St"))

//Point 54
        val lo46 = LatLng(47.705047607421875, -122.34469604492188)
        mMap.addMarker(MarkerOptions().position(lo46).title("N 105th St & N Northgate Way"))

        val lo47 = LatLng(47.705095075481, -122.338647885626)
        mMap.addMarker(MarkerOptions().position(lo47).title("Meridian Ave N & N Northgate Way"))

//Point 55
        val lo48 = LatLng(47.599205017089844, -122.3341293334961)
        mMap.addMarker(MarkerOptions().position(lo48).title("1st Ave S & Jackson St"))

        val lo49 = LatLng(47.60704803466797, -122.33531951904297)
        mMap.addMarker(MarkerOptions().position(lo49).title("3rd Ave & Seneca St"))

        val lo50 = LatLng(47.60444259643555, -122.3344955444336)
        mMap.addMarker(MarkerOptions().position(lo50).title("2nd Ave & Marion St"))

        val lo51 = LatLng(47.60469055175781, -122.33625030517578)
        mMap.addMarker(MarkerOptions().position(lo51).title("1st Ave & Madison St"))

        val lo52 = LatLng(47.601715087890625, -122.33611297607422)
        mMap.addMarker(MarkerOptions().position(lo52).title("Alaskan Way & Yesler Way"))

        val lo53 = LatLng(47.60319519042969, -122.33751678466797)
        mMap.addMarker(MarkerOptions().position(lo53).title("Alaskan Way & Marion St"))

        val lo54 = LatLng(47.6060905456543, -122.33293151855469)
        mMap.addMarker(MarkerOptions().position(lo54).title("4th Ave & Madison St"))

        val lo55 = LatLng(47.60419464111328, -122.33271026611328)
        mMap.addMarker(MarkerOptions().position(lo55).title("3rd Ave & Columbia St"))

        val lo56 = LatLng(47.606327056884766, -122.33466339111328)
        mMap.addMarker(MarkerOptions().position(lo56).title("3rd Ave & Spring St"))

        val lo57 = LatLng(47.60172653198242, -122.334228515625)
        mMap.addMarker(MarkerOptions().position(lo57).title("1st Ave & Yesler Way"))

//Point 56
        val lo58 = LatLng(47.58610153198242, -122.33419036865234)
        mMap.addMarker(MarkerOptions().position(lo58).title("1st Ave S & S Holgate St"))

        val lo59 = LatLng(47.5924072265625, -122.33419799804688)
        mMap.addMarker(MarkerOptions().position(lo59).title("1st Ave S & S Royal Brougham Way"))

        val lo60 = LatLng(47.59029006958008, -122.33566284179688)
        mMap.addMarker(MarkerOptions().position(lo60).title("SR-99 & S Atlantic St"))

        val lo61 = LatLng(47.59027099609375, -122.33422088623047)
        mMap.addMarker(MarkerOptions().position(lo61).title("1st Ave S & S Atlantic St"))

        val lo62 = LatLng(47.592391, -122.325267)
        mMap.addMarker(MarkerOptions().position(lo62).title("SR-99 @ S Royal Br Way NB on ramp"))

        val lo63 = LatLng(47.589442, -122.292994)
        mMap.addMarker(MarkerOptions().position(lo63).title("SR-99 @ S Atlantic St E"))

        val lo64 = LatLng(47.592325, -122.334383)
        mMap.addMarker(MarkerOptions().position(lo64).title("SR-99 @ S Royal Brougham Way"))

        val lo65 = LatLng(47.59023, -122.334378)
        mMap.addMarker(MarkerOptions().position(lo65).title("SR-99 @ S Atlantic St W"))

        val lo66 = LatLng(47.592272, -122.3366)
        mMap.addMarker(MarkerOptions().position(lo66).title("Alaskan Way S & S Atlantic St"))

        val lo67 = LatLng(47.565637, -122.336777)
        mMap.addMarker(MarkerOptions().position(lo67).title("Colorado Ave S & S Royal Brougham Way"))

//Point 57
        val lo68 = LatLng(47.6126704, -122.334333)
        mMap.addMarker(MarkerOptions().position(lo68).title("7th Ave & Pike St"))

        val lo69 = LatLng(47.6117995, -122.3364219)
        mMap.addMarker(MarkerOptions().position(lo69).title("5th Ave & Pine St NS"))

        val lo70 = LatLng(47.6095534, -122.3417764)
        mMap.addMarker(MarkerOptions().position(lo70).title("6th Ave & Pine St"))

        val lo71 = LatLng(47.6179442, -122.3383807)
        mMap.addMarker(MarkerOptions().position(lo71).title("5th Ave & Westlake Ave"))

        val lo72 = LatLng(47.617448, -122.3455185)
        mMap.addMarker(MarkerOptions().position(lo72).title("5th Ave & Union St"))

        val lo73 = LatLng(47.614519, -122.332952)
        mMap.addMarker(MarkerOptions().position(lo73).title("9th Ave & Stewart St"))

        val lo74 = LatLng(47.6155055, -122.332284)
        mMap.addMarker(MarkerOptions().position(lo74).title("8th Ave & Howell St"))

        val lo75 = LatLng(47.6069112, -122.3321529)
        mMap.addMarker(MarkerOptions().position(lo75).title("5th Ave & Pike St"))

        val lo76 = LatLng(47.6097847, -122.3421337)
        mMap.addMarker(MarkerOptions().position(lo76).title("6th Ave & Stewart St"))

//Point 58
        val lo77 = LatLng(47.6265477, -122.3335328)
        mMap.addMarker(MarkerOptions().position(lo77).title("Fairview Ave N @ Valley St"))

        val lo78 = LatLng(47.624556, -122.346268)
        mMap.addMarker(MarkerOptions().position(lo78).title("Fairview Ave N & N Mercer St"))

        val lo79 = LatLng(47.623227, -122.343741)
        mMap.addMarker(MarkerOptions().position(lo79).title("Fairview Ave N & Republican St"))

        val lo80 = LatLng(47.624548, -122.347561)
        mMap.addMarker(MarkerOptions().position(lo80).title("I-5 @ Mercer St, Ramps"))

        val lo81 = LatLng(47.624556, -122.346268)
        mMap.addMarker(MarkerOptions().position(lo81).title("Terry Ave N & Mercer St"))

//Point 59
        val lo82 = LatLng(47.6043621, -122.3312012)
        mMap.addMarker(MarkerOptions().position(lo82).title("4th Ave & Cherry St EW"))

        val lo83 = LatLng(47.6059251, -122.3313334)
        mMap.addMarker(MarkerOptions().position(lo83).title("5th Ave & Marion St"))

        val lo84 = LatLng(47.6052827, -122.3397604)
        mMap.addMarker(MarkerOptions().position(lo84).title("6th Ave & Seneca St"))

        val lo85 = LatLng(47.605837, -122.331148)
        mMap.addMarker(MarkerOptions().position(lo85).title("5th Ave & Madison St NS"))

        val lo86 = LatLng(47.6060592, -122.3397823)
        mMap.addMarker(MarkerOptions().position(lo86).title("3rd Ave & University St"))

        val lo87 = LatLng(47.605993, -122.340104)
        mMap.addMarker(MarkerOptions().position(lo87).title("I-5 @ University St, SB"))

        val lo88 = LatLng(47.605139, -122.339641)
        mMap.addMarker(MarkerOptions().position(lo88).title("I-5 @ Seneca St"))

        val lo89 = LatLng(47.605993, -122.340104)
        mMap.addMarker(MarkerOptions().position(lo89).title("I-5 @ University St Ramp"))

        val lo90 = LatLng(47.605993, -122.340104)
        mMap.addMarker(MarkerOptions().position(lo90).title("I-5 @ University St, NB"))

        val lo91 = LatLng(47.608152, -122.338324)
        mMap.addMarker(MarkerOptions().position(lo91).title("I-5 @ Union St, REV"))

        val lo92 = LatLng(47.605837, -122.331148)
        mMap.addMarker(MarkerOptions().position(lo92).title("5th Ave & Madison St EW"))

        val lo93 = LatLng(47.60725784301758, -122.33245086669922)
        mMap.addMarker(MarkerOptions().position(lo93).title("5th Ave & Spring St"))

//Point 60
        val lo94 = LatLng(47.5964794288196, -122.335535063331)
        mMap.addMarker(MarkerOptions().position(lo94).title("Alaskan Way S & S Dearborn St"))

//Point 61
        val lo95 = LatLng(47.618584, -122.351362)
        mMap.addMarker(MarkerOptions().position(lo95).title("Denny Way & Yale St / Stewart St"))

        val lo96 = LatLng(47.609573, -122.341268)
        mMap.addMarker(MarkerOptions().position(lo96).title("I-5 @ Pine St"))

        val lo97 = LatLng(47.618534088134766, -122.33430480957031)
        mMap.addMarker(MarkerOptions().position(lo97).title("Fairview Ave & Denny Way"))

        val lo98 = LatLng(47.61787796020508, -122.33429718017578)
        mMap.addMarker(MarkerOptions().position(lo98).title("Fairview Ave & Boren Ave"))

        val lo99 = LatLng(47.616851806640625, -122.3305435180664)
        mMap.addMarker(MarkerOptions().position(lo99).title("Howell St & Minor Ave"))

//Point 62
        val loc = LatLng(47.434925, -122.335317)
        mMap.addMarker(MarkerOptions().position(loc).title("1st Ave S & Spokane Viaduct"))

        val loc2 = LatLng(47.5725274, -122.334043)
        mMap.addMarker(MarkerOptions().position(loc2).title("1st Ave S & S Spokane St NS"))

        val loc3 = LatLng(47.5710229, -122.3292419)
        mMap.addMarker(MarkerOptions().position(loc3).title("4th Ave S & S Spokane St NS"))

        val loc4 = LatLng(47.600034, -122.328977)
        mMap.addMarker(MarkerOptions().position(loc4).title("4th Ave S & Spokane Viaduct"))

//Point 63
        val loc5 = LatLng(47.434925, -122.335317)
        mMap.addMarker(MarkerOptions().position(loc5).title("1st Ave S & E Marginal Way S"))

        val loc6 = LatLng(47.54533386230469, -122.32962036132812)
        mMap.addMarker(MarkerOptions().position(loc6).title("4th Ave S & Michigan St"))

        val loc7 = LatLng(47.547501, -122.321441)
        mMap.addMarker(MarkerOptions().position(loc7).title("E Marginal Way S & S Michigan St"))

        val loc8 = LatLng(47.54560852050781, -122.32855224609375)
        mMap.addMarker(MarkerOptions().position(loc8).title("SR-99 @ S Michigan St"))

//Point 64
        val loc9 = LatLng(47.53413391113281, -122.32872009277344)
        mMap.addMarker(MarkerOptions().position(loc9).title("SR-99 @ S Holden St"))

        val loc10 = LatLng(47.5368709324057, -122.333838848747)
        mMap.addMarker(MarkerOptions().position(loc10).title("SR-99 @ West Marginal Way"))


//Point 65
        val loc11 = LatLng(47.5266733814464, -122.333752432553)
        mMap.addMarker(MarkerOptions().position(loc11).title("SR-509 @ S Cloverdale St"))

//Point 66
        val loc12 = LatLng(47.59919357299805, -122.32898712158203)
        mMap.addMarker(MarkerOptions().position(loc12).title("4th Ave S & S Jackson St"))

        val loc13 = LatLng(47.6043621, -122.3312012)
        mMap.addMarker(MarkerOptions().position(loc13).title("4th Ave & Cherry St NS"))

        val loc14 = LatLng(47.6035696, -122.329006)
        mMap.addMarker(MarkerOptions().position(loc14).title("5th Ave & James St"))

        val loc15 = LatLng(47.599084, -122.3276741)
        mMap.addMarker(MarkerOptions().position(loc15).title("5th Ave S & S Washington St"))

        val loc16 = LatLng(47.6024788, -122.3328493)
        mMap.addMarker(MarkerOptions().position(loc16).title("2nd Ave & James St"))

        val loc17 = LatLng(47.603014, -122.337667)
        mMap.addMarker(MarkerOptions().position(loc17).title("I-5 @ Marion St"))

        val loc18 = LatLng(47.601637, -122.3364886)
        mMap.addMarker(MarkerOptions().position(loc18).title("3rd Ave & Yesler Way"))

        val loc19 = LatLng(47.6031129, -122.3300364)
        mMap.addMarker(MarkerOptions().position(loc19).title("4th Ave & James St"))

        val loc20 = LatLng(47.601223, -122.328888)
        mMap.addMarker(MarkerOptions().position(loc20).title("4th Ave S & S Washington St"))

        val loc21 = LatLng(47.6032586, -122.3326187)
        mMap.addMarker(MarkerOptions().position(loc21).title("6th Ave & Cherry St"))

        val loc22 = LatLng(47.600238, -122.293784)
        mMap.addMarker(MarkerOptions().position(loc22).title("2nd Ave Ext S & S Main St"))

//Point 67
        val loc23 = LatLng(47.6136702, -122.3322386)
        mMap.addMarker(MarkerOptions().position(loc23).title("9th Ave & Pine St"))

        val loc24 = LatLng(47.404271, -122.329872)
        mMap.addMarker(MarkerOptions().position(loc24).title("I-5 @ Pike St, NB"))

        val loc25 = LatLng(47.516608, -122.341749)
        mMap.addMarker(MarkerOptions().position(loc25).title("I-5 @ Pike St REV"))

        val loc26 = LatLng(47.611141, -122.334465)
        mMap.addMarker(MarkerOptions().position(loc26).title("I-5 @ Pike St, SB"))

        val loc27 = LatLng(47.6135485, -122.3291335)
        mMap.addMarker(MarkerOptions().position(loc27).title("Boren Ave & Pike St"))

//Point 68
        val loc28 = LatLng(47.44614, -122.2715)
        mMap.addMarker(MarkerOptions().position(loc28).title("I-5 @ NE 175th St, S"))

        val loc29 = LatLng(47.7544203765442, -122.330440354288)
        mMap.addMarker(MarkerOptions().position(loc29).title("I-5 @ NE 175th St, N"))

//Point 69
        val loc30 = LatLng(47.5167459280615, -122.33010663801)
        mMap.addMarker(MarkerOptions().position(loc30).title("SR-509 @ S 96th St"))

//Point 70
        val loc31 = LatLng(47.2865635, -122.3079013)
        mMap.addMarker(MarkerOptions().position(loc31).title("I-5 @ Northgate Way"))

        val loc32 = LatLng(47.708614349365234, -122.32864379882812)
        mMap.addMarker(MarkerOptions().position(loc32).title("1st Ave NE & NE Northgate Way"))

        val loc33 = LatLng(47.7066956, -122.2988906)
        mMap.addMarker(MarkerOptions().position(loc33).title("I-5 @ NE 107th St"))

 //Point 71
        val loc34 = LatLng(47.600034, -122.328977)
        mMap.addMarker(MarkerOptions().position(loc34).title("4th Ave S s/o I-90 TD"))

        val loc35 = LatLng(47.590312, -122.336928)
        mMap.addMarker(MarkerOptions().position(loc35).title("4th Ave S & S Atlantic St"))

        val loc36 = LatLng(47.601715, -122.327658)
        mMap.addMarker(MarkerOptions().position(loc36).title("5th Ave S & S Dearborn St / Airport Way S"))

        val loc37 = LatLng(47.5922655, -122.3300315)
        mMap.addMarker(MarkerOptions().position(loc37).title("I-90 @ 3rd Ave S"))

        val loc38 = LatLng(47.5999847, -122.3290147)
        mMap.addMarker(MarkerOptions().position(loc38).title("I-90 @ 4th Ave S"))

        val loc39 = LatLng(47.5924186706543, -122.32905578613281)
        mMap.addMarker(MarkerOptions().position(loc39).title("4th Ave S & S Royal Brougham Way"))

//Point 72
        val loc40 = LatLng(47.61076736450195, -122.32643127441406)
        mMap.addMarker(MarkerOptions().position(loc40).title("Boren Ave & Seneca St"))

        val loc41 = LatLng(47.60555648803711, -122.32473754882812)
        mMap.addMarker(MarkerOptions().position(loc41).title("9th Ave & James St"))

        val loc42 = LatLng(48.975133, -123.070119)
        mMap.addMarker(MarkerOptions().position(loc42).title("I-5 @ Marion St Rev"))

        val loc43 = LatLng(47.609336853027344, -122.32514190673828)
        mMap.addMarker(MarkerOptions().position(loc43).title("Boren Ave & Madison St"))

        val loc44 = LatLng(47.606919, -122.329093)
        mMap.addMarker(MarkerOptions().position(loc44).title("7th Ave & James St"))

//Point 73
        val loc45 = LatLng(47.6245, -122.356528)
        mMap.addMarker(MarkerOptions().position(loc45).title("I-5 @ Mercer St"))

        val loc46 = LatLng(47.157821, -119.019566)
        mMap.addMarker(MarkerOptions().position(loc46).title("I-5 @ Mercer St, REV"))

        val loc47 = LatLng(47.624548, -122.347561)
        mMap.addMarker(MarkerOptions().position(loc47).title("I-5 @ Mercer St NB Ramps"))

//Point 74
        val loc48 = LatLng(47.7471190991167, -122.329143223748)
        mMap.addMarker(MarkerOptions().position(loc48).title("I-5 @ METRO Bus Barn"))

//Point 75
        val loc49 = LatLng(47.618725, -122.330413)
        mMap.addMarker(MarkerOptions().position(loc49).title("Eastlake Ave E & E Stewart St"))

        val loc50 = LatLng(47.157821, -119.019566)
        mMap.addMarker(MarkerOptions().position(loc50).title("I-5 @ John St, REV"))

        val loc51 = LatLng(47.618524, -122.356575)
        mMap.addMarker(MarkerOptions().position(loc51).title("I-5 @ Denny Way"))

//Point 76
        val loc52 = LatLng(47.586170117905, -122.329062263223)
        mMap.addMarker(MarkerOptions().position(loc52).title("4th Ave S & S Holgate St"))

//Point 77
        val loc53 = LatLng(47.6906056, -122.274705)
        mMap.addMarker(MarkerOptions().position(loc53).title("I-5 @ NE 85th st"))

        val loc54 = LatLng(47.516608, -122.341749)
        mMap.addMarker(MarkerOptions().position(loc54).title("I-5 @ NE 80th St, Rev"))

//Point 78
        val loc55 = LatLng(47.7408629682472, -122.328675618726)
        mMap.addMarker(MarkerOptions().position(loc55).title("I-5 @ NE 155th St"))

//Point 79
        val loc56 = LatLng(47.548494, -122.321153)
        mMap.addMarker(MarkerOptions().position(loc56).title("Corson Ave S & S Bailey St / S Michigan St"))

        val loc57 = LatLng(47.54624938964844, -122.3261489868164)
        mMap.addMarker(MarkerOptions().position(loc57).title("6th Ave S & S Michigan St"))

//Point 80
        val loc58 = LatLng(47.5713810393612, -122.325959659239)
        mMap.addMarker(MarkerOptions().position(loc58).title("6th Ave S & S Spokane St"))

//Point 81
        val loc59 = LatLng(47.7158824495625, -122.325692003751)
        mMap.addMarker(MarkerOptions().position(loc59).title("I-5 @ NE 120th St"))

//Point 82
        val loc60 = LatLng(47.6647377, -122.324966)
        mMap.addMarker(MarkerOptions().position(loc60).title("Latona Ave NE & NE 50th St"))

        val loc61 = LatLng(47.6605768, -122.3230702)
        mMap.addMarker(MarkerOptions().position(loc61).title("I-5 @ NE 45th St"))

        val loc62 = LatLng(47.6649569, -122.3216059)
        mMap.addMarker(MarkerOptions().position(loc62).title("I-5 @ NE 50th St"))

//Point 83
        val loc63 = LatLng(47.597126, -122.3203698)
        mMap.addMarker(MarkerOptions().position(loc63).title("I-5 @ Yesler St"))

        val loc64 = LatLng(47.5992732, -122.3256468)
        mMap.addMarker(MarkerOptions().position(loc64).title("S Jackson St & Maynard Ave S"))

//Point 84
        val loc65 = LatLng(47.7081351, -122.3234096)
        mMap.addMarker(MarkerOptions().position(loc65).title("5th Ave NE & NE Northgate Way NS"))

        val loc66 = LatLng(47.7086021690831, -122.324517229029)
        mMap.addMarker(MarkerOptions().position(loc66).title("5th Ave NE & NE Northgate Way EW"))

        val loc67 = LatLng(47.7086002, -122.3233567)
        mMap.addMarker(MarkerOptions().position(loc67).title("5th Ave NE & Northgate Way 2"))

//Point 85
        val loc68 = LatLng(47.5264711264479, -122.324292895006)
        mMap.addMarker(MarkerOptions().position(loc68).title("SR-99 @ S Cloverdale St"))

//Point 86
        val loc69 = LatLng(47.516608, -122.341749)
        mMap.addMarker(MarkerOptions().position(loc69).title("I-5 & I-90 Interchange"))

        val loc70 = LatLng(47.694226, -122.358021)
        mMap.addMarker(MarkerOptions().position(loc70).title("I-90 @ Airport Way WB"))

        val loc71 = LatLng(47.5929299760437, -122.323884725507)
        mMap.addMarker(MarkerOptions().position(loc71).title("I-90 @ I-5 NB to EB Ramp"))

//Point 87
        val loc72 = LatLng(47.7631215972442, -122.323642734863)
        mMap.addMarker(MarkerOptions().position(loc72).title("I-5 @ NE 195th St"))

//Point 88
        val loc73 = LatLng(47.723119338455, -122.323564666173)
        mMap.addMarker(MarkerOptions().position(loc73).title("I-5 @ NE 130th St"))

//Point 89
        val loc74 = LatLng(47.6423682937089, -122.322867474489)
        mMap.addMarker(MarkerOptions().position(loc74).title("I-5 @ Roanoke St"))

//Point 90
        val loc75 = LatLng(47.733879337139, -122.322858747001)
        mMap.addMarker(MarkerOptions().position(loc75).title("I-5 @ NE 145th St"))

//Point 91
        val loc76 = LatLng(47.56504821777344, -122.32273864746094)
        mMap.addMarker(MarkerOptions().position(loc76).title("Airport Way S & S Industrial Way"))

        val loc77 = LatLng(47.5629292, -122.2931713)
        mMap.addMarker(MarkerOptions().position(loc77).title("I-5 @ S Oregon St"))

//Point 92
        val loc78 = LatLng(47.650474548339844, -122.32211303710938)
        mMap.addMarker(MarkerOptions().position(loc78).title("Harvard Ave E & Eastlake Ave E"))

        val loc79 = LatLng(47.6535585, -122.3225054)
        mMap.addMarker(MarkerOptions().position(loc79).title("I-5 @ Ship Canal Bridge"))

        val loc80 = LatLng(47.6580784, -122.3207926)
        mMap.addMarker(MarkerOptions().position(loc80).title("I-5 @ 7th Ave NE @ NE 42nd St"))

//Point 93
        val loc81 = LatLng(47.6498191703927, -122.322561390285)
        mMap.addMarker(MarkerOptions().position(loc81).title("I-5 @ Eastlake Ave, Rev"))

//Point 94
        val loc82 = LatLng(47.55733, -122.337908)
        mMap.addMarker(MarkerOptions().position(loc82).title("I-5 @ S Hudson St"))

        val loc83 = LatLng(47.559037, -122.3105655)
        mMap.addMarker(MarkerOptions().position(loc83).title("I-5 @ S Ferdinand St"))

//Point 95
        val loc84 = LatLng(47.579505920410156, -122.3213882446289)
        mMap.addMarker(MarkerOptions().position(loc84).title("Airport Way S & S Lander St"))

        val loc85 = LatLng(47.583901, -122.334943)
        mMap.addMarker(MarkerOptions().position(loc85).title("I-5 @ S Walker St"))

        val loc86 = LatLng(47.580194, -122.34269)
        mMap.addMarker(MarkerOptions().position(loc86).title("I-5 @ S Forest St"))

//Point 96
        val loc87 = LatLng(47.683045, -122.298625)
        mMap.addMarker(MarkerOptions().position(loc87).title("Roosevelt Ave NE & NE 75th St"))

        val loc88 = LatLng(47.7191619, -122.2954939)
        mMap.addMarker(MarkerOptions().position(loc88).title("I-5 @ Lake City Way"))

        val loc89 = LatLng(47.72867, -122.334217)
        mMap.addMarker(MarkerOptions().position(loc89).title("SR-522 & Roosevelt Tunnel"))

        val loc90 = LatLng(48.056773, -117.638197)
        mMap.addMarker(MarkerOptions().position(loc90).title("I-5 @ Lake City Way, REV"))

        val loc91 = LatLng(47.516608, -122.341749)
        mMap.addMarker(MarkerOptions().position(loc91).title("SR-522 @ I-5 Ramps"))

//Point 97
        val loc92 = LatLng(47.6250492040136, -122.320892987778)
        mMap.addMarker(MarkerOptions().position(loc92).title("Broadway & E Roy St"))

//Point 98
        val loc93 = LatLng(47.619907747612, -122.320872891674)
        mMap.addMarker(MarkerOptions().position(loc93).title("Broadway & E John St"))

//Point 99
        val loc94 = LatLng(47.6143239, -122.3208883)
        mMap.addMarker(MarkerOptions().position(loc94).title("Broadway & E Pike St NS"))

        val loc95 = LatLng(47.613779257072, -122.320775573091)
        mMap.addMarker(MarkerOptions().position(loc95).title("Broadway & E Pike St EW"))

//Point 100
        val loc96 = LatLng(47.6072184199276, -122.320752363658)
        mMap.addMarker(MarkerOptions().position(loc96).title("Broadway & James St"))

//Point 101
        val loc97 = LatLng(47.54733975281, -122.320250231438)
        mMap.addMarker(MarkerOptions().position(loc97).title("Carleton Ave S & S Bailey St"))

//Point 102
        val loc98 = LatLng(47.5713, -122.336603)
        mMap.addMarker(MarkerOptions().position(loc98).title("I-5 @ S Spokane St"))

        val loc99 = LatLng(47.5707415, -122.3160441)
        mMap.addMarker(MarkerOptions().position(loc99).title("I-5 @ S Court St"))

//Point 103
        val loca = LatLng(47.5860093485833, -122.319651975222)
        mMap.addMarker(MarkerOptions().position(loca).title("I-5 @ S Holgate St"))

//Point 104
        val loca1 = LatLng(47.59920120239258, -122.31720733642578)
        mMap.addMarker(MarkerOptions().position(loca1).title("12th Ave S & S Jackson St"))

        val loca2 = LatLng(47.59922409057617, -122.31416320800781)
        mMap.addMarker(MarkerOptions().position(loca2).title("14th Ave S & S Jackson St"))

        val loca3 = LatLng(47.60087585449219, -122.31697845458984)
        mMap.addMarker(MarkerOptions().position(loca3).title("12th Ave S & Boren Ave S"))

        val loca4 = LatLng(47.694226, -122.358021)
        mMap.addMarker(MarkerOptions().position(loca4).title("I-90 @ Corwin Pl"))

//Point 105
        val loca5 = LatLng(47.7847201419572, -122.316540499754)
        mMap.addMarker(MarkerOptions().position(loca5).title("I-5 @ 236th St SW"))

//Point 106
        val loca6 = LatLng(47.5198199296859, -122.316446706859)
        mMap.addMarker(MarkerOptions().position(loca6).title("SR-99 @ Des Moines Mem Drive"))

//Point 107
        val loca8 = LatLng(47.66127395629883, -122.31194305419922)
        mMap.addMarker(MarkerOptions().position(loca8).title("15th Ave NE & NE 45th St"))

        val loca9 = LatLng(47.664920806884766, -122.31523895263672)
        mMap.addMarker(MarkerOptions().position(loca9).title("12th Ave NE & NE 50th St"))

//Point 108
        val loca10 = LatLng(47.5264329899026, -122.31501862852)
        mMap.addMarker(MarkerOptions().position(loca10).title("14th Ave S & S Cloverdale St"))

//Point 109
        val loca11 = LatLng(47.7981553549713, -122.3145955773)
        mMap.addMarker(MarkerOptions().position(loca11).title("I-5 @ 220th St SW"))

//Point 110
        val loca12 = LatLng(47.5641094352247, -122.313477205737)
        mMap.addMarker(MarkerOptions().position(loca12).title("S Columbian Way W/O Beacon Ave S"))

//Point 111
        val loca13 = LatLng(47.5342672543368, -122.31319753214)
        mMap.addMarker(MarkerOptions().position(loca13).title("E Marginal Way S & 16th Ave S"))

//Point 112
        val loca14 = LatLng(47.686676025390625, -122.3121566772461)
        mMap.addMarker(MarkerOptions().position(loca14).title("15th Ave NE & NE 80th St"))

        val loca15 = LatLng(47.683040618896484, -122.31207275390625)
        mMap.addMarker(MarkerOptions().position(loca15).title("15th Ave NE & NE 75th St"))

//Point 113
        val loca16 = LatLng(47.655975341796875, -122.31208038330078)
        mMap.addMarker(MarkerOptions().position(loca16).title("15th Ave NE & NE Campus Pkwy"))

        val loca17 = LatLng(47.652976989746094, -122.31246185302734)
        mMap.addMarker(MarkerOptions().position(loca17).title("15th Ave NE & NE Pacific St"))

//Point 114
        val loca18 = LatLng(47.7085284071896, -122.312430359882)
        mMap.addMarker(MarkerOptions().position(loca18).title("15th Ave NE & NE Northgate Way"))

//Point 115
        val loca19 = LatLng(47.516608, -122.341749)
        mMap.addMarker(MarkerOptions().position(loca19).title("I-5 @ S Albro St"))

        val loca20 = LatLng(47.546005, -122.2787328)
        mMap.addMarker(MarkerOptions().position(loca20).title("I-5 @ S Graham St"))

        val loca21 = LatLng(47.54736, -122.308625)
        mMap.addMarker(MarkerOptions().position(loca21).title("I-5 @ Swift Ave, NB Ramp"))

        val loca22 = LatLng(47.54736, -122.308625)
        mMap.addMarker(MarkerOptions().position(loca22).title("I-5 @ Albro Pl/Swift Ave"))

//Point 116
        val loca23 = LatLng(47.595873882117, -122.311529602467)
        mMap.addMarker(MarkerOptions().position(loca23).title("Rainier Ave S & S Dearborn St"))

//Point 117
        val loca24 = LatLng(47.6150445597966, -122.311459488251)
        mMap.addMarker(MarkerOptions().position(loca24).title("16th Ave E & E Madison St"))

//Point 118
        val loca25 = LatLng(47.6882572425206, -122.311111909573)
        mMap.addMarker(MarkerOptions().position(loca25).title("16th Ave NE & Lake City Way"))

//Point 119
        val loca26 = LatLng(47.5196563, -122.2899162)
        mMap.addMarker(MarkerOptions().position(loca26).title("I-5 & SR-18"))

        val loca27 = LatLng(47.5196563, -122.2899162)
        mMap.addMarker(MarkerOptions().position(loca27).title("I-5 @ SR 18, South"))

        val loca28 = LatLng(47.2898232414808, -122.310131705601)
        mMap.addMarker(MarkerOptions().position(loca28).title("I-5 @ SR-18, West"))

//Point 120
        val loca29 = LatLng(47.6757805303696, -122.308714404261)
        mMap.addMarker(MarkerOptions().position(loca29).title("18th Ave NE & NE 65th St"))

//Point 121
        val loca30 = LatLng(47.5088669, -122.2358082)
        mMap.addMarker(MarkerOptions().position(loca30).title("I-90 @ Rainier Ave S"))

        val loca31 = LatLng(47.58842468261719, -122.30583953857422)
        mMap.addMarker(MarkerOptions().position(loca31).title("Rainier Ave S & S Massachusetts St"))

        val loca32 = LatLng(47.59188461303711, -122.30249786376953)
        mMap.addMarker(MarkerOptions().position(loca32).title("23rd Ave S & S Judkins St"))

//Point 122
        val loca33 = LatLng(47.644265, -122.304328)
        mMap.addMarker(MarkerOptions().position(loca33).title("SR-520 @ Montlake Blvd"))

        val loca34 = LatLng(47.6478106, -122.3046164)
        mMap.addMarker(MarkerOptions().position(loca34).title("Montlake Blvd NE & NE Lake Washington Blvd"))

        val loca35 = LatLng(47.644265, -122.304328)
        mMap.addMarker(MarkerOptions().position(loca35).title("SR-520 @ Montlake Ramp"))

        val loca36 = LatLng(47.6238649, -122.305739)
        mMap.addMarker(MarkerOptions().position(loca36).title("SR-520 @ 20th Ave E"))

//Point 123
        val loca37 = LatLng(47.649237, -122.305033)
        mMap.addMarker(MarkerOptions().position(loca37).title("Montlake Blvd NE @ NE Pacific St"))

        val loca38 = LatLng(47.649173, -122.33242)
        mMap.addMarker(MarkerOptions().position(loca38).title("Montlake Blvd NE & NE Lake Washington Blvd"))

//Point 124
        val loca39 = LatLng(47.621373, -122.302483)
        mMap.addMarker(MarkerOptions().position(loca39).title("23rd Ave E & E Madison St EW"))

        val loca40 = LatLng(47.6184201499922, -122.303650196027)
        mMap.addMarker(MarkerOptions().position(loca40).title("23rd Ave E & E Madison St NS"))

//Point 125
        val loca41 = LatLng(47.5845418647111, -122.302909504046)
        mMap.addMarker(MarkerOptions().position(loca41).title("Rainier Ave S & S Hill St"))

//Point 126
        val loca42 = LatLng(47.611588, -122.302746)
        mMap.addMarker(MarkerOptions().position(loca42).title("23rd Ave & E Alder St"))

        val loca43 = LatLng(47.621373, -122.302483)
        mMap.addMarker(MarkerOptions().position(loca43).title("23rd Ave E & E Cherry St NS"))

        val loca44 = LatLng(47.6082657222401, -122.302768189568)
        mMap.addMarker(MarkerOptions().position(loca44).title("23rd Ave E & E Cherry St EW"))

//Point 127
        val loca45 = LatLng(47.59927749633789, -122.3022232055664)
        mMap.addMarker(MarkerOptions().position(loca45).title("23rd Ave S & S Jackson St"))

        val loca46 = LatLng(47.5996719, -122.3020962)
        mMap.addMarker(MarkerOptions().position(loca46).title("23rd Ave E & E Cherry St NS"))

        val loca47 = LatLng(47.599194, -122.328068)
        mMap.addMarker(MarkerOptions().position(loca47).title("MLK Jr Way S & S Jackson St"))

        val loca48 = LatLng(47.595577239990234, -122.3022232055664)
        mMap.addMarker(MarkerOptions().position(loca48).title("23rd Ave S & S Dearborn St"))

//Point 128
        val loca49 = LatLng(47.5086817642504, -122.301994083173)
        mMap.addMarker(MarkerOptions().position(loca49).title("SR-99 @ S102nd St"))

//Point 129
        val loca50 = LatLng(47.3021535806456, -122.301714934861)
        mMap.addMarker(MarkerOptions().position(loca50).title("I-5 @ S 333rd St"))

//Point 130
        val loca51 = LatLng(47.6590590452522, -122.301149172458)
        mMap.addMarker(MarkerOptions().position(loca51).title("Montlake Blvd NE & 25th Ave NE"))

//Point 131
        val loca52 = LatLng(47.6757742208191, -122.300651546798)
        mMap.addMarker(MarkerOptions().position(loca52).title("25th Ave NE & NE 66th St"))

//Point 132
        val loca53 = LatLng(47.5902178406266, -122.300324425838)
        mMap.addMarker(MarkerOptions().position(loca53).title("I-90 @ 18th Ave S"))

//Point 133
        val loca54 = LatLng(47.5916189, -122.3224727)
        mMap.addMarker(MarkerOptions().position(loca54).title("Airport Way S & King County Airport"))

        val loca55 = LatLng(47.513801, -122.288227)
        mMap.addMarker(MarkerOptions().position(loca55).title("I-5 @ Mid Boeing Field"))

        val loca56 = LatLng(47.5341756, -122.28142)
        mMap.addMarker(MarkerOptions().position(loca56).title("I-5 @ S Austin St"))

        val loca57 = LatLng(47.5354078, -122.2814488)
        mMap.addMarker(MarkerOptions().position(loca57).title("I-5 @ S Webster St"))

//Point 134
        val loca58 = LatLng(47.464507, -122.318236)
        mMap.addMarker(MarkerOptions().position(loca58).title("SR-518 @ S 154th St"))

        val loca59 = LatLng(47.580169677734375, -122.32139587402344)
        mMap.addMarker(MarkerOptions().position(loca59).title("SR-518 @ Airport Drive"))

//Point 135
        val loca60 = LatLng(47.6441060057078, -122.299992513236)
        mMap.addMarker(MarkerOptions().position(loca60).title("SR-520 @ 25th Ave E"))

//Point 136
        val loca61 = LatLng(47.8104208452361, -122.299040819612)
        mMap.addMarker(MarkerOptions().position(loca61).title("I-5 @ 44th Ave W"))

//Point 137
        val loca62 = LatLng(47.500201, -122.221081)
        mMap.addMarker(MarkerOptions().position(loca62).title("Rainier Ave S at MLK Way S"))

        val loca63 = LatLng(47.5783500445387, -122.298150713097)
        mMap.addMarker(MarkerOptions().position(loca63).title("Rainier Ave S & S McClellan St"))

        val loca64 = LatLng(47.500201, -122.221081)
        mMap.addMarker(MarkerOptions().position(loca64).title("MLK Way S at Rainier Ave S"))

//Point 138
        val loca65 = LatLng(47.5000197189865, -122.297332034021)
        mMap.addMarker(MarkerOptions().position(loca65).title("SR-99 @ SR-599"))

//Point 139
        val loca66 = LatLng(47.516608, -122.341749)
        mMap.addMarker(MarkerOptions().position(loca66).title("I-5 @ S 317th St"))

        val loca67 = LatLng(47.3138090222423, -122.297090283746)
        mMap.addMarker(MarkerOptions().position(loca67).title("I-5 @ S 320th St"))

//Point 140
        val loca68 = LatLng(47.3577208234647, -122.296834175254)
        mMap.addMarker(MarkerOptions().position(loca68).title("I-5 @ S 272nd St"))

//Point 141
        val loca69 = LatLng(47.6016561533033, -122.296767841218)
        mMap.addMarker(MarkerOptions().position(loca69).title("MLK Jr Way & E Yesler Way"))

//Point 142
        val loca70 = LatLng(47.5662923414745, -122.296688931258)
        mMap.addMarker(MarkerOptions().position(loca70).title("MLK Jr Way S & S Dakota St"))

//Point 143
        val loca71 = LatLng(47.6079966244096, -122.296254306729)
        mMap.addMarker(MarkerOptions().position(loca71).title("MLK Jr Way & E Cherry St"))

//Point 144
        val loca72 = LatLng(47.7191325524873, -122.295319709727)
        mMap.addMarker(MarkerOptions().position(loca72).title("Lake City Way NE & NE 125th St EW"))

        val loca73 = LatLng(47.685364, -122.315019)
        mMap.addMarker(MarkerOptions().position(loca73).title("Lake City Way & NE 125th St NS"))

//Point 145
        val loca74 = LatLng(47.7019488, -122.3293103)
        mMap.addMarker(MarkerOptions().position(loca74).title("I-5 & SR-516"))

        val loca75 = LatLng(47.6224765, -122.3445612)
        mMap.addMarker(MarkerOptions().position(loca75).title("SR-99 @ SR-516"))

//Point 146
        val loca76 = LatLng(47.3259343148302, -122.294163316783)
        mMap.addMarker(MarkerOptions().position(loca76).title("I-5 @ S 308th St"))

//Point 147
        val loca77 = LatLng(47.500201, -122.221081)
        mMap.addMarker(MarkerOptions().position(loca77).title("Rainier Ave S & S Charlestown St"))

        val loca78 = LatLng(47.573784, -122.290966)
        mMap.addMarker(MarkerOptions().position(loca78).title("Rainier Ave S & S Walden St"))

//Point 148
        val loca79 = LatLng(47.660284, -122.410609)
        mMap.addMarker(MarkerOptions().position(loca79).title("SR-520 @ Lake Wa Blvd Ramp"))

        val loca80 = LatLng(47.641529, -122.292827)
        mMap.addMarker(MarkerOptions().position(loca80).title("SR-520 @ Foster Island"))

//Point 149
        val loca81 = LatLng(47.3694204590972, -122.293835972766)
        mMap.addMarker(MarkerOptions().position(loca81).title("I-5 @ S 260th St"))

//Point 150
        val loca82 = LatLng(47.560317, -122.335506)
        mMap.addMarker(MarkerOptions().position(loca82).title("MLK Way S & S Alaska St NS"))

        val loca83 = LatLng(47.5608084474838, -122.293240252334)
        mMap.addMarker(MarkerOptions().position(loca83).title("MLK Way S & S Alaska St EW"))

//Point 151
        val loca84 = LatLng(47.6758633974811, -122.29298523241)
        mMap.addMarker(MarkerOptions().position(loca84).title("32nd Ave NE & NE 65th St"))

//Point 152
        val loca85 = LatLng(47.3433204266954, -122.292935181084)
        mMap.addMarker(MarkerOptions().position(loca85).title("I-5 @ S 288th St"))

//Point 153
        val loca86 = LatLng(47.6612433063045, -122.29286352236)
        mMap.addMarker(MarkerOptions().position(loca86).title("NE 45th St & Union Bay"))

//Point 154
        val loca87 = LatLng(47.40832251777, -122.292630959512)
        mMap.addMarker(MarkerOptions().position(loca87).title("I-5 @ S 216th St"))

//Point 155
        val loca88 = LatLng(47.7282882740876, -122.292250178049)
        mMap.addMarker(MarkerOptions().position(loca88).title("Lake City Way & NE 137th St"))

//Point 156
        val loca89 = LatLng(47.38069128396, -122.291569114184)
        mMap.addMarker(MarkerOptions().position(loca89).title("I-5 @ S 248th St"))

//Point 157
        val loca90 = LatLng(47.5198260104985, -122.289610837433)
        mMap.addMarker(MarkerOptions().position(loca90).title("I-5 @ S Benefit St"))

//Point 158
        val loca91 = LatLng(47.739616960714, -122.289549324315)
        mMap.addMarker(MarkerOptions().position(loca91).title("SR-522 @ NE 153rd St"))

//Point 159
        val loca92 = LatLng(47.6244681, -122.3437728)
        mMap.addMarker(MarkerOptions().position(loca92).title("SR-518 @ SR-99"))

        val loca93 = LatLng(47.464507, -122.318236)
        mMap.addMarker(MarkerOptions().position(loca93).title("SR-99 @ S 154th St"))

//Point 160
        val loca94 = LatLng(47.694226, -122.358021)
        mMap.addMarker(MarkerOptions().position(loca94).title("I-90 @ East Portal MBT"))

        val loca95 = LatLng(47.5903854975816, -122.288521294998)
        mMap.addMarker(MarkerOptions().position(loca95).title("I-90 @ W Highrise"))

//Point 161
        val loca96 = LatLng(47.5605084, -122.2869692)
        mMap.addMarker(MarkerOptions().position(loca96).title("Rainier Ave S & S Alaska St"))

        val loca97 = LatLng(47.5645546, -122.2882013)
        mMap.addMarker(MarkerOptions().position(loca97).title("Rainier Ave S & S Genesee St"))

//Point 162
        val loca98 = LatLng(47.5136075966867, -122.288112064107)
        mMap.addMarker(MarkerOptions().position(loca98).title("Airport Way S & S Norfolk St"))

//Point 163
        val loca99 = LatLng(47.5512302715191, -122.288095734492)
        mMap.addMarker(MarkerOptions().position(loca99).title("MLK Jr Way S & S Orcas St"))

//Point 164
        val locat = LatLng(47.5512302715191, -122.288095734492)
        mMap.addMarker(MarkerOptions().position(locat).title("MLK Jr Way S & S Orcas St"))

//Point 165
        val locat1 = LatLng(47.4206211131748, -122.287435203034)
        mMap.addMarker(MarkerOptions().position(locat1).title("I-5 @ S 200th St"))

//Point 166
        val locat2 = LatLng(47.4975986072776, -122.287017526046)
        mMap.addMarker(MarkerOptions().position(locat2).title("SR-599 @ Marginal Way"))

//Point 167
        val locat3 = LatLng(47.5460584126582, -122.285452019525)
        mMap.addMarker(MarkerOptions().position(locat3).title("MLK Jr Way S & S Graham St"))

//Point 168
        val locat4 = LatLng(47.7482421536213, -122.284797767688)
        mMap.addMarker(MarkerOptions().position(locat4).title("SR-522 @ NE 165th St"))

//Point 169
        val locat5 = LatLng(47.5570411201773, -122.284629529032)
        mMap.addMarker(MarkerOptions().position(locat5).title("Rainier Ave S & S Hudson St"))

//Point 170
        val locat6 = LatLng(47.5080048, -122.2784522)
        mMap.addMarker(MarkerOptions().position(locat6).title("I-5 @ Boeing Access Rd, South"))

        val locat7 = LatLng(47.5084153312669, -122.282192856365)
        mMap.addMarker(MarkerOptions().position(locat7).title("I-5 @ Boeing Access Rd, North"))

        val locat8 = LatLng(47.507146, -122.2907442)
        mMap.addMarker(MarkerOptions().position(locat8).title("SR-900 @ S Boeing Access Rd"))

//Point 171
        val locat9 = LatLng(47.4918511302266, -122.282190116871)
        mMap.addMarker(MarkerOptions().position(locat9).title("SR-599 @ Metro Bus Barn"))

//Point 172
        val locat10 = LatLng(47.537225, -122.325803)
        mMap.addMarker(MarkerOptions().position(locat10).title("MLK Way & S Othello St EW"))

        val locat11 = LatLng(47.5371848895509, -122.281168319882)
        mMap.addMarker(MarkerOptions().position(locat11).title("MLK Way S & S Othello St NS"))

//Point 173
        val locat12 = LatLng(47.526117, -122.275846)
        mMap.addMarker(MarkerOptions().position(locat12).title("MLK Jr Way S & S Cloverdale St"))

        val locat13 = LatLng(47.522552, -122.346551)
        mMap.addMarker(MarkerOptions().position(locat13).title("MLK Jr Way S & S Henderson St"))

//Point 174
        val locat14 = LatLng(47.5134293101433, -122.278671356082)
        mMap.addMarker(MarkerOptions().position(locat14).title("MLK Jr Way S & S Norfolk St"))

//Point 175
        val locat15 = LatLng(47.5511760252476, -122.277664109778)
        mMap.addMarker(MarkerOptions().position(locat15).title("Rainier Ave S & S Orcas St"))

//Point 176
        val locat16 = LatLng(47.8216650979368, -122.277354254216)
        mMap.addMarker(MarkerOptions().position(locat16).title("I-5 @ 196th St SW"))

//Point 177
        val locat17 = LatLng(47.4656229243612, -122.276632499203)
        mMap.addMarker(MarkerOptions().position(locat17).title("SR-518 @ 46th Ave S"))

//Point 178
        val locat18 = LatLng(47.546026624252, -122.275988663552)
        mMap.addMarker(MarkerOptions().position(locat18).title("Rainier Ave S & S Graham St"))

//Point 179
        val locat19 = LatLng(47.482717, -122.226484)
        mMap.addMarker(MarkerOptions().position(locat19).title("SR-599 @ S 133rd St"))

        val locat20 = LatLng(47.4856785, -122.2753065)
        mMap.addMarker(MarkerOptions().position(locat20).title("I-5 @ SR-599"))

//Point 180
        val locat21 = LatLng(47.54595, -122.327272)
        mMap.addMarker(MarkerOptions().position(locat21).title("I-5 @ MLK Jr Way, South"))

        val locat22 = LatLng(47.460649, -122.326105)
        mMap.addMarker(MarkerOptions().position(locat22).title("I-5 @ MLK Jr Way, North"))

        val locat23 = LatLng(47.495615, -122.265378)
        mMap.addMarker(MarkerOptions().position(locat23).title("MLK Jr Way S & S Juniper St"))

//Point 181
        val locat24 = LatLng(47.4322216624788, -122.271130820795)
        mMap.addMarker(MarkerOptions().position(locat24).title("I-5 @ S 188th St"))

//Point 182
        val locat25 = LatLng(47.5369578783704, -122.270274016104)
        mMap.addMarker(MarkerOptions().position(locat25).title("Rainier Ave S & S Othello St"))

//Point 183
        val locat26 = LatLng(47.5898833227723, -122.270240627029)
        mMap.addMarker(MarkerOptions().position(locat26).title("I-90 Midspan"))

//Point 184
        val locat27 = LatLng(47.516608, -122.341749)
        mMap.addMarker(MarkerOptions().position(locat27).title("I-5 @ Southcenter"))

        val locat28 = LatLng(47.5190468, -122.2700091)
        mMap.addMarker(MarkerOptions().position(locat28).title("SR-518 @ 51st Ave S"))

//Point 185
        val locat29 = LatLng(47.4744999994649, -122.269999999961)
        mMap.addMarker(MarkerOptions().position(locat29).title("I-5 @ S 144th St"))

//Point 186
        val locat30 = LatLng(47.529130115378, -122.269987052353)
        mMap.addMarker(MarkerOptions().position(locat30).title("Rainier Ave S & S Rose St"))

//Point 187
        val locat31 = LatLng(47.5229297, -122.2701302)
        mMap.addMarker(MarkerOptions().position(locat31).title("Rainier Ave S & S Henderson St"))

        val locat32 = LatLng(47.5204641, -122.2701277)
        mMap.addMarker(MarkerOptions().position(locat32).title("Rainier Ave S & 51st Ave S"))

//Point 188
        val locat33 = LatLng(47.6418585131378, -122.269155116689)
        mMap.addMarker(MarkerOptions().position(locat33).title("SR-520 West Highrise"))

//Point 189
        val locat34 = LatLng(47.4436237506455, -122.268132608323)
        mMap.addMarker(MarkerOptions().position(locat34).title("I-5 @ S 178th St"))

//Point 190
        val locat35 = LatLng(47.4915267514359, -122.264704816063)
        mMap.addMarker(MarkerOptions().position(locat35).title("I-5 @ Duwamish River"))

//Point 191
        val locat36 = LatLng(47.7019488, -122.3293103)
        mMap.addMarker(MarkerOptions().position(locat36).title("I-5 @ SR-518"))

        val locat37 = LatLng(47.465114, -122.268175)
        mMap.addMarker(MarkerOptions().position(locat37).title("I-405 @ Southcenter"))

//Point 192
        val locat38 = LatLng(47.4570999993901, -122.263999998912)
        mMap.addMarker(MarkerOptions().position(locat38).title("I-5 @ Klickitat Drive"))

//Point 193
        val locat39 = LatLng(47.8311707647017, -122.263254863373)
        mMap.addMarker(MarkerOptions().position(locat39).title("I-5 @ 186th St SW"))

//Point 194
        val locat40 = LatLng(47.7581208743822, -122.262034322138)
        mMap.addMarker(MarkerOptions().position(locat40).title("SR-522 @ 61st Ave NE"))

//Point 195
        val locat41 = LatLng(47.5202643966723, -122.26158231719)
        mMap.addMarker(MarkerOptions().position(locat41).title("Rainier Ave S & 57th Ave S"))

//Point 196
        val locat42 = LatLng(47.2817316662809, -122.258496738965)
        mMap.addMarker(MarkerOptions().position(locat42).title("SR-167 @ 353rd St"))

//Point 197
        val locat43 = LatLng(47.8503123477099, -122.256571499141)
        mMap.addMarker(MarkerOptions().position(locat43).title("I-5 @ 164th St SW"))

//Point 198
        val locat44 = LatLng(47.6396599205232, -122.254913811958)
        mMap.addMarker(MarkerOptions().position(locat44).title("SR-520 @ Mid Bridge"))

//Point 199
        val locat45 = LatLng(47.825194568555, -122.253660966526)
        mMap.addMarker(MarkerOptions().position(locat45).title("I-405 @ Filbert Rd"))

//Point 200
        val locat46 = LatLng(47.694226, -122.358021)
        mMap.addMarker(MarkerOptions().position(locat46).title("I-90 @ E Highrise"))

        val locat47 = LatLng(47.5896982, -122.257351)
        mMap.addMarker(MarkerOptions().position(locat47).title("I-90 @ W Mercer Way"))

//Point 201
        val locat48 = LatLng(47.6386502991795, -122.248463864341)
        mMap.addMarker(MarkerOptions().position(locat48).title("SR-520 @ East Bridge"))

//Point 202
        val locat49 = LatLng(47.6609502, -122.309657)
        mMap.addMarker(MarkerOptions().position(locat49).title("I-405 @ W Valley Hwy"))

        val locat50 = LatLng(47.570741, -122.314765)
        mMap.addMarker(MarkerOptions().position(locat50).title("SR-181 @ Grady Way"))

//Point 203
        val locat51 = LatLng(47.377923736945, -122.244434401583)
        mMap.addMarker(MarkerOptions().position(locat51).title("SR-167 @ SR-516"))

//Point 204
        val locat52 = LatLng(47.3412227195102, -122.243831498211)
        mMap.addMarker(MarkerOptions().position(locat52).title("SR-167 @ 37th St NW"))

//Point 205
        val locat53 = LatLng(47.757319945172, -122.243737280723)
        mMap.addMarker(MarkerOptions().position(locat53).title("SR-522 @ 73rd Ave NE"))

//Point 206
        val locat54 = LatLng(47.8203189127968, -122.242740914502)
        mMap.addMarker(MarkerOptions().position(locat54).title("I-405 @ Damson Rd"))

//Point 207
        val locat55 = LatLng(47.6375941673906, -122.241775339396)
        mMap.addMarker(MarkerOptions().position(locat55).title("SR-520 East Highrise"))

//Point 208
        val locat56 = LatLng(47.4652226329961, -122.241429049668)
        mMap.addMarker(MarkerOptions().position(locat56).title("I-405 @ Longacres Dr SW"))

//Point 209
        val locat57 = LatLng(47.3215237246154, -122.241427941253)
        mMap.addMarker(MarkerOptions().position(locat57).title("SR-167 @ 15th St NW"))

//Point 210
        val locat58 = LatLng(47.5899760669968, -122.237873625635)
        mMap.addMarker(MarkerOptions().position(locat58).title("I-90 @ 76th Ave SE"))

//Point 211
        val locat59 = LatLng(47.4667218286256, -122.235631096517)
        mMap.addMarker(MarkerOptions().position(locat59).title("I-405 @ Oaksdale Ave"))

//Point 212
        val locat60 = LatLng(47.8819026621768, -122.232726239471)
        mMap.addMarker(MarkerOptions().position(locat60).title("I-5 @ 128th St SW"))

//Point 213
        val locat61 = LatLng(47.6361598224296, -122.229013477296)
        mMap.addMarker(MarkerOptions().position(locat61).title("SR-520 @ 84th Ave NE"))

//Point 214
        val locat62 = LatLng(47.4014855641491, -122.224345619772)
        mMap.addMarker(MarkerOptions().position(locat62).title("SR-167 @ S 222nd St"))

//Point 215
        val locat63 = LatLng(47.6376601302372, -122.22309565435)
        mMap.addMarker(MarkerOptions().position(locat63).title("SR-520 @ 88th Ave NE"))

//Point 216
        val locat64 = LatLng(47.4275834977062, -122.221513633043)
        mMap.addMarker(MarkerOptions().position(locat64).title("SR-167 @ S 194th St"))

//Point 217
        val locat65 = LatLng(47.4142174223084, -122.221394161698)
        mMap.addMarker(MarkerOptions().position(locat65).title("SR-167 @ S 212th St"))

//Point 218
        val locat66 = LatLng(47.5821197211006, -122.219732445475)
        mMap.addMarker(MarkerOptions().position(locat66).title("I-90 @ Shorewood, West"))

//Point 219
        val locat67 = LatLng(47.4676222282923, -122.21933419536)
        mMap.addMarker(MarkerOptions().position(locat67).title("I-405 @ SR-167"))

//Point 220
        val locat68 = LatLng(47.6406215734384, -122.217733998278)
        mMap.addMarker(MarkerOptions().position(locat68).title("SR-520 @ 92nd Ave NE"))

//Point 221
        val locat69 = LatLng(47.7945206330215, -122.214235862736)
        mMap.addMarker(MarkerOptions().position(locat69).title("I-405 @ SR-527"))

//Point 222
        val locat70 = LatLng(47.4693236522333, -122.207828929878)
        mMap.addMarker(MarkerOptions().position(locat70).title("I-405 @ Talbot Rd S"))

//Point 223
        val locat71 = LatLng(47.9157499959374, -122.207507557558)
        mMap.addMarker(MarkerOptions().position(locat71).title("I-5 @ SR-526"))

//Point 224
        val locat72 = LatLng(47.7890031172773, -122.203638156978)
        mMap.addMarker(MarkerOptions().position(locat72).title("I-405 @ 232nd St SE"))

//Point 225
        val locat73 = LatLng(47.6425431072267, -122.202429645359)
        mMap.addMarker(MarkerOptions().position(locat73).title("SR-520 @ Bellevue Way NE"))

//Point 226
        val locat74 = LatLng(47.9549215305148, -122.200251032169)
        mMap.addMarker(MarkerOptions().position(locat74).title("I-5 @ 47th St SE"))

//Point 227
        val locat75 = LatLng(47.5120482740227, -122.198627583864)
        mMap.addMarker(MarkerOptions().position(locat75).title("I-405 @ NE 24th St"))

//Point 228
        val locat76 = LatLng(47.5315220886693, -122.197636458131)
        mMap.addMarker(MarkerOptions().position(locat76).title("I-405 @ NE 44th St"))

//Point 229
        val locat77 = LatLng(47.6403354445453, -122.196666539469)
        mMap.addMarker(MarkerOptions().position(locat77).title("SR-520 @ 108th Ave NE, N"))

        val locat78 = LatLng(47.506935, -122.327861)
        mMap.addMarker(MarkerOptions().position(locat78).title("SR-520 @ 108th Ave NE, S"))

//Point 230
        val locat79 = LatLng(47.5008216068243, -122.196529755308)
        mMap.addMarker(MarkerOptions().position(locat79).title("I-405 @ NE Park Dr"))

//Point 231
        val locat80 = LatLng(47.435214, -122.342714)
        mMap.addMarker(MarkerOptions().position(locat80).title("I-5 @ 73rd St SE, SB"))

        val locat81 = LatLng(47.404271, -122.329872)
        mMap.addMarker(MarkerOptions().position(locat81).title("I-5 @ 73rd St SE, NB"))

//Point 232
        val locat82 = LatLng(47.4850918236684, -122.195775029204)
        mMap.addMarker(MarkerOptions().position(locat82).title("I-405 @ SR-169"))

//Point 233
        val locat83 = LatLng(47.5441388871431, -122.195558012852)
        mMap.addMarker(MarkerOptions().position(locat83).title("I-405 @ SE 64th St"))

//Point 234
        val locat84 = LatLng(47.7783798424066, -122.192787059042)
        mMap.addMarker(MarkerOptions().position(locat84).title("I-405 @ 243rd St SE"))

//Point 235
        val locat85 = LatLng(47.4925208947191, -122.19213527366)
        mMap.addMarker(MarkerOptions().position(locat85).title("I-405 @ Sunset Blvd"))

//Point 236
        val locat86 = LatLng(47.6511418, -122.3106835)
        mMap.addMarker(MarkerOptions().position(locat86).title("I-5 @ Pacific Ave"))

        val locat87 = LatLng(47.6145496, -122.3299239)
        mMap.addMarker(MarkerOptions().position(locat87).title("I-5 @ US-2"))

//Point 237
        val locat88 = LatLng(47.5793613709898, -122.190599926367)
        mMap.addMarker(MarkerOptions().position(locat88).title("I-90 @ Bellevue Way"))

//Point 238
        val locat89 = LatLng(47.238217, -121.983021)
        mMap.addMarker(MarkerOptions().position(locat89).title("I-405 @ SR-520"))

        val locat90 = LatLng(47.68982, -122.304577)
        mMap.addMarker(MarkerOptions().position(locat90).title("I-405 @ NE 22nd St"))

        val locat91 = LatLng(47.238217, -121.983021)
        mMap.addMarker(MarkerOptions().position(locat91).title("SR-520 @ I-405 SE Quad"))

//Point 239
        val locat92 = LatLng(47.769118982504, -122.189034546582)
        mMap.addMarker(MarkerOptions().position(locat92).title("I-405 @ NE 195th St"))

//Point 240
        val locat93 = LatLng(47.7220287316714, -122.188772853922)
        mMap.addMarker(MarkerOptions().position(locat93).title("I-405 @ NE 132nd St"))

//Point 241
        val locat94 = LatLng(47.6266219300849, -122.188734419226)
        mMap.addMarker(MarkerOptions().position(locat94).title("I-405 @ NE 20th St"))

//Point 242
        val locat95 = LatLng(47.724018, -122.320835)
        mMap.addMarker(MarkerOptions().position(locat95).title("I-405 @ NE 8th St"))

        val locat96 = LatLng(47.6110863, -122.3221065)
        mMap.addMarker(MarkerOptions().position(locat96).title("I-405 @ NE 14th St"))

        val locat97 = LatLng(47.724018, -122.320835)
        mMap.addMarker(MarkerOptions().position(locat97).title("I-405 @ SE 8th St"))

//Point 243
        val locat98 = LatLng(47.6662615, -122.3762785)
        mMap.addMarker(MarkerOptions().position(locat98).title("I-405 @ Main St"))

        val locat99 = LatLng(47.606164, -122.332985)
        mMap.addMarker(MarkerOptions().position(locat99).title("I-405 @ NE 4th St"))

//Point 244
        val locat100 = LatLng(47.6530177505856, -122.187044984538)
        mMap.addMarker(MarkerOptions().position(locat100).title("I-405 @ NE 59th St"))

//Point 245
        val locati = LatLng(47.7503203763164, -122.187034601058)
        mMap.addMarker(MarkerOptions().position(locati).title("I-405 @ NE 175th St"))

//Point 246
        val locati1 = LatLng(47.6654016604448, -122.186963491212)
        mMap.addMarker(MarkerOptions().position(locati1).title("I-405 @ NE 70th Pl"))

//Point 247
        val locati2 = LatLng(47.6441078734586, -122.186902094402)
        mMap.addMarker(MarkerOptions().position(locati2).title("I-405 @ NE 39th St"))

//Point 248
        val locati3 = LatLng(47.7441865865779, -122.186778473852)
        mMap.addMarker(MarkerOptions().position(locati3).title("I-405 @ NE 160th St"))

//Point 249
        val locati4 = LatLng(47.5621959439019, -122.186437015934)
        mMap.addMarker(MarkerOptions().position(locati4).title("I-405 @ SE 45th St"))

//Point 250
        val locati5 = LatLng(47.7146898428887, -122.185176272038)
        mMap.addMarker(MarkerOptions().position(locati5).title("I-405 @ NE 128th St"))

//Point 251
        val locati6 = LatLng(47.9834967125887, -122.184790653528)
        mMap.addMarker(MarkerOptions().position(locati6).title("I-5 @ Everett Ave"))

//Point 252
        val locati7 = LatLng(47.7584150414092, -122.184707938294)
        mMap.addMarker(MarkerOptions().position(locati7).title("I-405 @ SR-522"))

//Point 253
        val locati8 = LatLng(48.0500321752279, -122.184649131771)
        mMap.addMarker(MarkerOptions().position(locati8).title("I-5 @ SR-528"))

//Point 254
        val locati9 = LatLng(47.6771205979225, -122.184035234279)
        mMap.addMarker(MarkerOptions().position(locati9).title("I-405 @ NE 85th St"))

//Point 255
        val locati10 = LatLng(47.9890287680282, -122.183244975482)
        mMap.addMarker(MarkerOptions().position(locati10).title("I-5 @ Marine View Dr"))

//Point 256
        val locati11 = LatLng(47.724018, -122.320835)
        mMap.addMarker(MarkerOptions().position(locati11).title("I-405 @ NE 8th St"))

        val locati12 = LatLng(47.6025208704259, -122.183034587181)
        mMap.addMarker(MarkerOptions().position(locati12).title("I-405 @ SE 8th St"))

//Point 257
        val locati13 = LatLng(47.6876587266854, -122.182058304139)
        mMap.addMarker(MarkerOptions().position(locati13).title("I-405 @ NE 100th St"))

//Point 258
        val locati14 = LatLng(47.404271, -122.329872)
        mMap.addMarker(MarkerOptions().position(locati14).title("I-405 @ SE 20th St, NB"))

        val locati15 = LatLng(47.500968, -122.359573)
        mMap.addMarker(MarkerOptions().position(locati15).title("I-405 @ SE 20th St, SB"))

//Point 259
        val locati16 = LatLng(48.0408461083806, -122.180233642406)
        mMap.addMarker(MarkerOptions().position(locati16).title("I-5 @ SR 529"))

//Point 260
        val locati17 = LatLng(47.9935049337898, -122.179491329979)
        mMap.addMarker(MarkerOptions().position(locati17).title("I-5 @ 15th St"))

//Point 261
        val locati18 = LatLng(47.5701228803294, -122.178235015246)
        mMap.addMarker(MarkerOptions().position(locati18).title("I-405 @ Coal Creek Pkwy"))

//Point 262
        val locati19 = LatLng(47.97808997016, -122.177500360178)
        mMap.addMarker(MarkerOptions().position(locati19).title("US-2 @ Homeacres Rd"))

//Point 263
        val locati20 = LatLng(48.0277549964141, -122.176580862565)
        mMap.addMarker(MarkerOptions().position(locati20).title("I-5 @ 37th St NE"))

//Point 264
        val locati21 = LatLng(47.5753435798207, -122.175695617276)
        mMap.addMarker(MarkerOptions().position(locati21).title("I-405 @ SE 40th St"))

//Point 265
        val locati22 = LatLng(48.0189340863506, -122.17459437467)
        mMap.addMarker(MarkerOptions().position(locati22).title("I-5 @ 26th St NE"))

//Point 266
        val locati23 = LatLng(47.580521709187, -122.174436082017)
        mMap.addMarker(MarkerOptions().position(locati23).title("I-405 @ Factoria"))

//Point 267
        val locati24 = LatLng(48.0076118824834, -122.172663382562)
        mMap.addMarker(MarkerOptions().position(locati24).title("I-5 @ 12th St NE"))

//Point 268
        val locati25 = LatLng(47.6301201421667, -122.167135474673)
        mMap.addMarker(MarkerOptions().position(locati25).title("SR-520 @ 130th Ave NE"))

//Point 269
        val locati26 = LatLng(47.5803234021559, -122.166531972722)
        mMap.addMarker(MarkerOptions().position(locati26).title("I-90 @ 133rd Ave SE"))

//Point 270
        val locati27 = LatLng(47.7587203709091, -122.16183503279)
        mMap.addMarker(MarkerOptions().position(locati27).title("SR-522 @ SR-202"))

//Point 271
        val locati28 = LatLng(47.6337366073175, -122.144422199575)
        mMap.addMarker(MarkerOptions().position(locati28).title("SR-520 @ 148th Ave NE"))

//Point 272
        val locati29 = LatLng(47.5797208841026, -122.141529763478)
        mMap.addMarker(MarkerOptions().position(locati29).title("I-90 @ 150th Ave SE"))

//Point 273
        val locati30 = LatLng(47.9781092718083, -122.140244484598)
        mMap.addMarker(MarkerOptions().position(locati30).title("US-2 @ Ebey Slough"))

//Point 274
        val locati31 = LatLng(47.57916499809, -122.134918203302)
        mMap.addMarker(MarkerOptions().position(locati31).title("I-90 @ 161st Ave SE"))

//Point 275
        val locati32 = LatLng(47.8354893477585, -122.124427068116)
        mMap.addMarker(MarkerOptions().position(locati32).title("SR-9 @ 180th St SE"))

//Point 276
        val locati33 = LatLng(47.6696214177986, -122.107831582823)
        mMap.addMarker(MarkerOptions().position(locati33).title("SR-520 @ Redmond Way"))

//Point 277
        val locati34 = LatLng(47.5593242682273, -122.088534798902)
        mMap.addMarker(MarkerOptions().position(locati34).title("I-90 @ 192nd Ave SE"))

//Point 278
        val locati36 = LatLng(47.5490210703518, -122.062434092768)
        mMap.addMarker(MarkerOptions().position(locati36).title("I-90 @ SR-900"))

//Point 279
        val locati37 = LatLng(47.5407074586553, -122.037733070806)
        mMap.addMarker(MarkerOptions().position(locati37).title("I-90 @ Front St"))

    }

    private fun getAddress(latLng: LatLng): String? {
        val geocoder = Geocoder(this)
        val list = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
        return list[0].getAddressLine(0)
    }
    override fun onMarkerClick(p0: Marker?) = false
}

