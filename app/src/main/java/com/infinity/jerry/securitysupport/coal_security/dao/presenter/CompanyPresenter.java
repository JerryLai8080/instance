package com.infinity.jerry.securitysupport.coal_security.dao.presenter;

import com.infinity.jerry.securitysupport.coal_security.dao.i_view.IViewCompany;
import com.infinity.jerry.securitysupport.coal_security.dao.model.CompanyModel;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CBaseInfo;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CDoorsInfo;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CProInfo;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CSaftyInfo;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CSixSysInfo;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CheckItemTemp;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CompanyTemp;
import com.infinity.jerry.securitysupport.coal_security.dao.temp_entity.CshaftInfo;
import com.infinity.jerry.securitysupport.common.entity.CoalCheckItem;
import com.infinity.jerry.securitysupport.common.entity.CompanyBaseInfo;
import com.infinity.jerry.securitysupport.common.entity.CompanyDoor;
import com.infinity.jerry.securitysupport.common.entity.CompanyProduct;
import com.infinity.jerry.securitysupport.common.entity.CompanySafty;
import com.infinity.jerry.securitysupport.common.entity.CompanyShaft;
import com.infinity.jerry.securitysupport.common.entity.CompanySixSys;
import com.infinity.jerry.securitysupport.common.z_utils.z_mvp.ZResultSubscriber;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jerry on 2018/1/9.
 */

public class CompanyPresenter {

    private IViewCompany iViewCompany;
    private CompanyModel model;

    private List<CompanyTemp> list;

    private CompanyPresenter(IViewCompany iViewCompany) {
        this.iViewCompany = iViewCompany;
        model = new CompanyModel();
    }

    public static CompanyPresenter getInstance(IViewCompany iViewCompany) {
        return new CompanyPresenter(iViewCompany);
    }

    public void getCompanies() {
        model.getAllCompanies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ZResultSubscriber<List<CompanyTemp>>() {
                    @Override
                    public void onSuccessZ(List<CompanyTemp> companyTemps) {
                        list = companyTemps;
                        getBaseInfo();
                    }

                    @Override
                    public void onErrorZ(Throwable throwable) {
                        iViewCompany.getCompanyError();
                    }
                });
    }

    private void getBaseInfo() {
        model.getBaseInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ZResultSubscriber<List<CBaseInfo>>() {
                    @Override
                    public void onSuccessZ(List<CBaseInfo> dataList) {
                        List<CompanyBaseInfo> companyBaseInfos = new ArrayList<>();
                        for (CBaseInfo item : dataList) {
                            CompanyBaseInfo entity = new CompanyBaseInfo();
                            entity.setId(item.getId());
                            entity.setCoalCode(item.getCoal_code());
                            entity.setCoalLicence(item.getCoal_licence());
                            entity.setCoalLicLife(item.getCoal_lic_life());
                            entity.setBusiLicCode(item.getBusi_lic_code());
                            entity.setSaftyLicCode(item.getSafty_lic_code());
                            entity.setMiningLicCode(item.getMining_lic_code());
                            entity.setMasCertiCode(item.getMas_certi_code());
                            entity.setCoalLicCode(item.getCoal_lic_code());
                            entity.setCoalLicPrintState(item.getCoal_lic_print_state());
                            entity.setCoalLicState(item.getCoal_lic_state());
                            entity.setCoalLicDistri(item.getCoal_lic_distri());
                            entity.setBusiLicState(item.getBusi_lic_state());
                            entity.setMiningLicState(item.getMining_lic_state());
                            entity.setMiningLicDistri(item.getMining_lic_distri());
                            entity.setMasCertiState(item.getMas_certi_state());
                            entity.setSaftyLicState(item.getSafty_lic_state());
                            entity.setCoalLicBelong(item.getCoal_lic_belong());
                            entity.setBusiLicLife(item.getBusi_lic_life());
                            entity.setBusiLicBelong(item.getBusi_lic_belong());
                            entity.setMiningLicLife(item.getMining_lic_life());
                            entity.setMiningLicBelon(item.getMining_lic_belon());
                            entity.setMasCertiLife(item.getMas_certi_life());
                            entity.setMasCertiBelong(item.getMas_certi_belong());
                            entity.setSaftyLicLife(item.getSafty_lic_life());
                            entity.setSaftyLicBelong(item.getSafty_lic_belong());
                            companyBaseInfos.add(entity);
                        }
                        DataSupport.deleteAll(CompanyBaseInfo.class);
                        DataSupport.saveAll(companyBaseInfos);
                        getProInfo();
                    }

                    @Override
                    public void onErrorZ(Throwable throwable) {
                    }
                });
    }

    private void getProInfo() {
        model.getProInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ZResultSubscriber<List<CProInfo>>() {
                    @Override
                    public void onSuccessZ(List<CProInfo> cProInfos) {

                        List<CompanyProduct> companyProducts = new ArrayList<>();
                        for (CProInfo item : cProInfos) {
                            CompanyProduct entity = new CompanyProduct();
                            entity.setId(item.getId());
                            entity.setCompanyCode(item.getCompany_code());
                            entity.setCoalUp(item.getCoal_up());
                            entity.setTransMethod(item.getTrans_method());
                            entity.setCoalTech(item.getCoal_tech());
                            entity.setCoalBackRate(item.getCoal_back_rate());
                            entity.setCoalStartTime(item.getCoal_start_time());
                            entity.setUpTransSys(item.getUp_trans_sys());
                            entity.setUpTransMethod(item.getUp_trans_method());
                            entity.setTransEquipNum(item.getTrans_equip_num());
                            entity.setPowerSys(item.getPower_sys());
                            entity.setPowerMethod(item.getPower_method());
                            entity.setTransformerPower(item.getTransformer_power());
                            entity.setMainLoop(item.getMain_loop());
                            entity.setMainLoopType(item.getMain_loop_type());
                            entity.setBackupLoop(item.getBackup_loop());
                            entity.setBackupLoopType(item.getBackup_loop_type());
                            entity.setDownPowerSupply(item.getDown_power_supply());
                            entity.setIntoPower(item.getInto_power());
                            entity.setIntoMainPower(item.getInto_main_power());
                            companyProducts.add(entity);
                        }
                        DataSupport.deleteAll(CompanyProduct.class);
                        DataSupport.saveAll(companyProducts);
                        getSaftyInfo();
                    }

                    @Override
                    public void onErrorZ(Throwable throwable) {
                    }
                });
    }

    private void getSaftyInfo() {
        model.getSaftyInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ZResultSubscriber<List<CSaftyInfo>>() {
                    @Override
                    public void onSuccessZ(List<CSaftyInfo> cSaftyInfos) {
                        List<CompanySafty> companySafties = new ArrayList<>();
                        for (CSaftyInfo item : cSaftyInfos) {
                            CompanySafty entity = new CompanySafty();
                            entity.setId(item.getId());
                            entity.setCompanyCode(item.getCompany_code());
                            entity.setGasPump(item.getGas_pump());
                            entity.setGasMonitor(item.getGas_monitor());
                            entity.setGasIsNet(item.getGas_is_net());
                            entity.setManLocation(item.getMan_location());
                            entity.setMainFanNum(item.getMain_fan_num());
                            entity.setMainFanElcNum(item.getMain_fan_elc_num());
                            entity.setMainFanRatePower(item.getMain_fan_rate_power());
                            entity.setSubFanNum(item.getSub_fan_num());
                            entity.setSubFanElcNum(item.getSub_fan_elc_num());
                            entity.setSubFanRatePower(item.getSub_fan_rate_power());
                            entity.setRatedBlast(item.getRated_blast());
                            entity.setRatedPressure(item.getRated_pressure());
                            entity.setDowMethod(item.getDow_method());
                            entity.setDowNum(item.getDow_num());
                            entity.setDowCount(item.getDow_count());
                            entity.setDowPower(item.getDow_power());
                            entity.setDowMax(item.getDow_max());
                            entity.setDowRadius(item.getDow_radius());
                            entity.setHasGasPump(item.getHas_gas_pump());
                            entity.setGasPumpMethod(item.getGas_pump_method());
                            entity.setPumpNum(item.getPump_num());
                            entity.setPumpSize(item.getPump_size());
                            entity.setHasTube(item.getHas_tube());
                            entity.setTubeNum(item.getTube_num());
                            entity.setHasN2(item.getHas_n2());
                            entity.setN2Type(item.getN2_type());
                            entity.setN2Num(item.getN2_num());
                            entity.setHasInjection(item.getHas_injection());
                            entity.setInjectionType(item.getInjection_type());
                            entity.setInjectionNum(item.getInjection_num());
                            entity.setInjectionForday(item.getInjection_forday());
                            entity.setOtherFireSys(item.getOther_fire_sys());
                            entity.setHasMonitor(item.getHas_monitor());
                            entity.setMonitorNum(item.getMonitor_num());
                            entity.setMonitorCount(item.getMonitor_count());
                            entity.setGasSensorCount(item.getGas_sensor_count());
                            entity.setCoSensorCount(item.getCo_sensor_count());
                            entity.setWindSensorCount(item.getWind_sensor_count());
                            entity.setTempSensorCount(item.getTemp_sensor_count());
                            entity.setOnoffSensorCount(item.getOnoff_sensor_count());
                            entity.setOtherSensorCount(item.getOther_sensor_count());
                            entity.setSsProducePlace(item.getSs_produce_place());
                            entity.setSsProduceTime(item.getSs_produce_time());
                            entity.setSsInstallTime(item.getSs_install_time());
                            entity.setUnderElcSys(item.getUnder_elc_sys());
                            entity.setUnderElcMethod(item.getUnder_elc_method());
                            entity.setTransformerPower(item.getTransformer_power());
                            entity.setMainLoop(item.getMain_loop());
                            entity.setMainLoopType(item.getMain_loop_type());
                            entity.setSubLoop(item.getSub_loop());
                            entity.setSubLoopType(item.getSub_loop_type());
                            entity.setUndercoalElcSys(item.getUndercoal_elc_sys());
                            entity.setIntoMainPressure(item.getInto_main_pressure());
                            entity.setIntoMainCable(item.getInto_main_cable());
                            entity.setWindSys(item.getWind_sys());
                            entity.setWindSysType(item.getWind_sys_type());
                            companySafties.add(entity);
                        }
                        DataSupport.deleteAll(CompanySafty.class);
                        DataSupport.saveAll(companySafties);
                        getDoorInfo();
                    }

                    @Override
                    public void onErrorZ(Throwable throwable) {
                    }
                });
    }

    private void getDoorInfo() {
        model.getDoorInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ZResultSubscriber<List<CDoorsInfo>>() {
                    @Override
                    public void onSuccessZ(List<CDoorsInfo> cDoorsInfos) {
                        List<CompanyDoor> companyDoors = new ArrayList<>();
                        for (CDoorsInfo item : cDoorsInfos) {
                            CompanyDoor entity = new CompanyDoor();
                            entity.setId(item.getId());
                            entity.setCompanyCode(item.getCompany_code());
                            entity.setDoorName(item.getDoor_name());
                            entity.setAxisX(item.getAxis_x());
                            entity.setAxisY(item.getAxis_y());
                            entity.setAxisZ(item.getAxis_z());
                            companyDoors.add(entity);
                        }
                        DataSupport.deleteAll(CompanyDoor.class);
                        DataSupport.saveAll(companyDoors);
                        getShaftInfo();
                    }

                    @Override
                    public void onErrorZ(Throwable throwable) {
                    }
                });
    }

    private void getShaftInfo() {
        model.getShaftInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ZResultSubscriber<List<CshaftInfo>>() {
                    @Override
                    public void onSuccessZ(List<CshaftInfo> cshaftInfos) {
                        List<CompanyShaft> companyShafts = new ArrayList<>();
                        for (CshaftInfo item : cshaftInfos) {
                            CompanyShaft entity = new CompanyShaft();
                            entity.setId(item.getId());
                            entity.setCompanyCode(item.getCompany_code());
                            entity.setShaftArea(item.getShaft_area());
                            entity.setShaftSize(item.getShaft_size());
                            entity.setShaftCan(item.getShaft_can());
                            entity.setShaftCount(item.getShaft_count());
                            entity.setShaftHeight(item.getShaft_height());
                            entity.setShaftLic(item.getShaft_lic());
                            entity.setShaftDeepA(item.getShaft_deep_a());
                            entity.setShaftDeepB(item.getShaft_deep_b());
                            companyShafts.add(entity);
                        }
                        DataSupport.deleteAll(CompanyShaft.class);
                        DataSupport.saveAll(companyShafts);
                        getSixSysInfo();
                    }

                    @Override
                    public void onErrorZ(Throwable throwable) {
                    }
                });
    }

    private void getSixSysInfo() {
        model.getSixSysInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ZResultSubscriber<List<CSixSysInfo>>() {
                    @Override
                    public void onSuccessZ(List<CSixSysInfo> sixSysInfos) {
                        List<CompanySixSys> companySixSys = new ArrayList<>();
                        for (CSixSysInfo item : sixSysInfos) {
                            CompanySixSys entity = new CompanySixSys();
                            entity.setId(item.getId());
                            entity.setCompanyCode(item.getCompany_code());
                            entity.setMonitor(item.getMonitor());
                            entity.setMonitorFinish(item.getMonitor_finish());
                            entity.setWindSave(item.getWind_save());
                            entity.setWindSaveFinish(item.getWind_save_finish());
                            entity.setWaterSave(item.getWater_save());
                            entity.setWaterSaveFinish(item.getWater_save_finish());
                            entity.setNetSys(item.getNet_sys());
                            entity.setNetSysFinish(item.getNet_sys_finish());
                            entity.setUnderSave(item.getUnder_save());
                            entity.setUnderSaveFinish(item.getUnder_save_finish());
                            entity.setUnderLocation(item.getUnder_location());
                            entity.setUnderLocationFinish(item.getUnder_location_finish());
                            companySixSys.add(entity);
                        }

                        DataSupport.deleteAll(CompanySixSys.class);
                        DataSupport.saveAll(companySixSys);
                        getCoalCheckItems();
                    }

                    @Override
                    public void onErrorZ(Throwable throwable) {
                    }
                });
    }

    private void getCoalCheckItems() {
        model.getCheckItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ZResultSubscriber<List<CheckItemTemp>>() {
                    @Override
                    public void onSuccessZ(List<CheckItemTemp> checkItemTemps) {
                        List<CoalCheckItem> coalCheckItems = new ArrayList<>();
                        for (CheckItemTemp item : checkItemTemps) {
                            CoalCheckItem entity = new CoalCheckItem();
                            entity.setId(item.getId());
                            entity.setComType(item.getCOM_TYPE());
                            entity.setComName(item.getCOM_NAME());
                            entity.setCheckinType(item.getCHECKIN_TYPE());
                            entity.setCheckinName(item.getCHECKIN_NAME());
                            entity.setCheckinSuper(item.getCHECKIN_SUPER());
                            entity.setCheckinItem(item.getCHECKIN_ITEM());
                            entity.setParent(item.getPARENT());
                            entity.setCheckinContent(item.getCHECKIN_CONTENT());
                            entity.setCheckinRequest(item.getCHECKIN_REQUEST());
                            entity.setCheckinReference(item.getCHECKIN_REFERENCE());
                            entity.setC12(item.getC12());
                            entity.setC13(item.getC13());
                            entity.setC14(item.getC14());
                            entity.setC15(item.getC15());
                            coalCheckItems.add(entity);
                        }
                        DataSupport.deleteAll(CoalCheckItem.class);
                        DataSupport.saveAll(coalCheckItems);
                        iViewCompany.getCompanySucc(list);
                    }

                    @Override
                    public void onErrorZ(Throwable throwable) {

                    }
                });
    }


}
