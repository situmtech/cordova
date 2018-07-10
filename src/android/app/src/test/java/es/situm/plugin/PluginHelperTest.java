package es.situm.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;

import es.situm.plugin.mock.CommunicationManagerTest;
import es.situm.sdk.communication.CommunicationManager;
import es.situm.sdk.model.cartography.Building;
import es.situm.sdk.model.location.Coordinate;
import es.situm.sdk.model.location.Dimensions;
import es.situm.sdk.utils.Handler;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class PluginHelperTest {


    @Mock
    CommunicationManager cmMockInstance;

    @Mock
    Collection<Building> collectionBuildings;

    @Mock
    CordovaInterface cdvInterface;

    @Mock
    CallbackContext cbContext;

    //@Mock
    //SitumMapper situmMapper;

    @Mock
    CordovaWebView webView;

    @InjectMocks
    PluginHelper pluginHelper;


    @Captor
    private ArgumentCaptor<Handler<Collection<Building>>> mBuildingCollectionHandlerCaptor;


    /*@Test
    public void fetchBuildingsTest() {
        Coordinate center = new Coordinate(15.84, 8.73);
        Dimensions dimensions = new Dimensions(20.44, 9.81);
        final Building building = new Building.Builder().address("Camiño de adran").name("Cocodin").center(center).dimensions(dimensions).build();
        PluginHelper pluginHelper = new PluginHelper();
        pluginHelper.setCommunicationManager(new CommunicationManagerTest());
        doNothing().when(pluginHelper).fetchBuildings(cdvInterface, webView, new JSONArray(), cbContext)).thenAnswer(new Answer<Object>() {

        });
        pluginHelper.fetchBuildings(cdvInterface, webView, new JSONArray(), cbContext);
        verify(cbContext).success();
    }*/

    @Test
    public void testFetchBuildings() throws Exception {
        pluginHelper.fetchBuildings(cdvInterface, webView, new JSONArray(), cbContext);
        verify(cmMockInstance).fetchBuildings( mBuildingCollectionHandlerCaptor.capture());
        mBuildingCollectionHandlerCaptor.getValue().onSuccess(collectionBuildings);

        //verify(cmMockInstance).fetchBuildings(any(new Handler<Collection<Building>));
        //verify(situmMapper).buildingToJsonObject(any(Building.class));
    }

}