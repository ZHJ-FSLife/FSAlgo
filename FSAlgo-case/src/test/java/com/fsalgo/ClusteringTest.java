package com.fsalgo;

import com.fsalgo.core.clustering.DBSCAN;
import com.fsalgo.core.clustering.KSpanningTreeClustering;
import com.fsalgo.core.interfaces.ClusteringAlgorithm;
import com.fsalgo.core.math.geometrical.Distance;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.struct.builder.GraphBuilder;
import com.fsalgo.core.tree.vectorspace.AbstractQuadOcTree;
import com.fsalgo.core.tree.vectorspace.SpacePoint;
import com.fsalgo.core.tree.vectorspace.specific.BallTree;
import com.fsalgo.core.tree.vectorspace.specific.KDTree;
import com.fsalgo.core.tree.vectorspace.specific.OcTree;
import com.fsalgo.core.tree.vectorspace.specific.QuadTree;
import com.fsalgo.utils.FileUtils;
import com.fsalgo.utils.GraphUtil;
import com.fsalgo.utils.TreeUtil;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @Author: root
 * @Date: 2023/3/5 21:10
 * @Description:
 */
public class ClusteringTest {

    List<SpacePoint<String>> data = new ArrayList<>() {{
        add(new SpacePoint.SpacePointImpl<>("P01", new double[]{1, 3}));
        add(new SpacePoint.SpacePointImpl<>("P02", new double[]{1, 8}));
        add(new SpacePoint.SpacePointImpl<>("P03", new double[]{2, 2}));
        add(new SpacePoint.SpacePointImpl<>("P04", new double[]{2, 10}));
        add(new SpacePoint.SpacePointImpl<>("P05", new double[]{3, 6}));
        add(new SpacePoint.SpacePointImpl<>("P06", new double[]{4, 1}));
        add(new SpacePoint.SpacePointImpl<>("P07", new double[]{5, 4}));
        add(new SpacePoint.SpacePointImpl<>("P08", new double[]{6, 8}));
        add(new SpacePoint.SpacePointImpl<>("P09", new double[]{7, 4}));
        add(new SpacePoint.SpacePointImpl<>("P10", new double[]{7, 7}));
        add(new SpacePoint.SpacePointImpl<>("P11", new double[]{8, 2}));
        add(new SpacePoint.SpacePointImpl<>("P12", new double[]{8, 5}));
        add(new SpacePoint.SpacePointImpl<>("P13", new double[]{9, 9}));
        add(new SpacePoint.SpacePointImpl<>("P14", new double[]{76.7, 56.3}));
        add(new SpacePoint.SpacePointImpl<>("P15", new double[]{77.7, 67.8}));
        add(new SpacePoint.SpacePointImpl<>("P16", new double[]{87.8, 77.2}));
        add(new SpacePoint.SpacePointImpl<>("P17", new double[]{88.8, 78.10}));
        add(new SpacePoint.SpacePointImpl<>("P18", new double[]{98.9, 88.6}));
        add(new SpacePoint.SpacePointImpl<>("P19", new double[]{19.1, 89.1}));
        add(new SpacePoint.SpacePointImpl<>("P20", new double[]{11.1, 91.4}));
        add(new SpacePoint.SpacePointImpl<>("P21", new double[]{21.2, 11.8}));
        add(new SpacePoint.SpacePointImpl<>("P22", new double[]{22.2, 12.4}));
        add(new SpacePoint.SpacePointImpl<>("P23", new double[]{32.3, 22.7}));
        add(new SpacePoint.SpacePointImpl<>("P24", new double[]{43.4, 23.2}));
        add(new SpacePoint.SpacePointImpl<>("P25", new double[]{54.5, 34.5}));
        add(new SpacePoint.SpacePointImpl<>("P26", new double[]{65.6, 45.9}));
        add(new SpacePoint.SpacePointImpl<>("P27", new double[]{76.7, 56.3}));
        add(new SpacePoint.SpacePointImpl<>("P28", new double[]{77.7, 67.8}));
        add(new SpacePoint.SpacePointImpl<>("P29", new double[]{87.8, 77.2}));
        add(new SpacePoint.SpacePointImpl<>("P30", new double[]{88.8, 78.10}));
        add(new SpacePoint.SpacePointImpl<>("P31", new double[]{98.9, 88.6}));
        add(new SpacePoint.SpacePointImpl<>("P32", new double[]{19.1, 89.1}));
        add(new SpacePoint.SpacePointImpl<>("P33", new double[]{11.1, 91.4}));
        add(new SpacePoint.SpacePointImpl<>("P34", new double[]{21.2, 11.8}));
        add(new SpacePoint.SpacePointImpl<>("P35", new double[]{22.2, 12.4}));
        add(new SpacePoint.SpacePointImpl<>("P36", new double[]{32.3, 22.7}));
        add(new SpacePoint.SpacePointImpl<>("P37", new double[]{43.4, 23.2}));
        add(new SpacePoint.SpacePointImpl<>("P38", new double[]{54.5, 34.5}));
        add(new SpacePoint.SpacePointImpl<>("P39", new double[]{65.6, 45.9}));
        add(new SpacePoint.SpacePointImpl<>("P40", new double[]{76.7, 56.3}));
        add(new SpacePoint.SpacePointImpl<>("P41", new double[]{77.7, 67.8}));
        add(new SpacePoint.SpacePointImpl<>("P42", new double[]{87.8, 77.2}));
        add(new SpacePoint.SpacePointImpl<>("P43", new double[]{88.8, 78.10}));
        add(new SpacePoint.SpacePointImpl<>("P44", new double[]{98.9, 88.6}));
        add(new SpacePoint.SpacePointImpl<>("P45", new double[]{19.1, 89.1}));
        add(new SpacePoint.SpacePointImpl<>("P46", new double[]{11.1, 91.4}));
        add(new SpacePoint.SpacePointImpl<>("P47", new double[]{21.2, 11.8}));
        add(new SpacePoint.SpacePointImpl<>("P48", new double[]{22.2, 12.4}));
        add(new SpacePoint.SpacePointImpl<>("P49", new double[]{32.3, 22.7}));
        add(new SpacePoint.SpacePointImpl<>("P50", new double[]{43.4, 23.2}));
        add(new SpacePoint.SpacePointImpl<>("P51", new double[]{54.5, 34.5}));
        add(new SpacePoint.SpacePointImpl<>("P52", new double[]{65.6, 45.9}));
        add(new SpacePoint.SpacePointImpl<>("P53", new double[]{76.7, 56.3}));
        add(new SpacePoint.SpacePointImpl<>("P54", new double[]{77.7, 67.8}));
        add(new SpacePoint.SpacePointImpl<>("P55", new double[]{87.8, 77.2}));
        add(new SpacePoint.SpacePointImpl<>("P56", new double[]{88.8, 78.10}));
        add(new SpacePoint.SpacePointImpl<>("P57", new double[]{98.9, 88.6}));
        add(new SpacePoint.SpacePointImpl<>("P58", new double[]{19.1, 89.1}));
        add(new SpacePoint.SpacePointImpl<>("P59", new double[]{11.1, 91.4}));
        add(new SpacePoint.SpacePointImpl<>("P60", new double[]{21.2, 11.8}));
        add(new SpacePoint.SpacePointImpl<>("P61", new double[]{22.2, 12.4}));
        add(new SpacePoint.SpacePointImpl<>("P62", new double[]{32.3, 22.7}));
        add(new SpacePoint.SpacePointImpl<>("P63", new double[]{43.4, 23.2}));
        add(new SpacePoint.SpacePointImpl<>("P64", new double[]{54.5, 34.5}));
        add(new SpacePoint.SpacePointImpl<>("P65", new double[]{65.6, 45.9}));
        add(new SpacePoint.SpacePointImpl<>("P66", new double[]{76.7, 56.3}));
        add(new SpacePoint.SpacePointImpl<>("P67", new double[]{77.7, 67.8}));
        add(new SpacePoint.SpacePointImpl<>("P68", new double[]{87.8, 77.2}));
        add(new SpacePoint.SpacePointImpl<>("P69", new double[]{88.8, 78.10}));
        add(new SpacePoint.SpacePointImpl<>("P70", new double[]{98.9, 88.6}));
        add(new SpacePoint.SpacePointImpl<>("P71", new double[]{19.1, 89.1}));
        add(new SpacePoint.SpacePointImpl<>("P72", new double[]{11.1, 91.4}));
        add(new SpacePoint.SpacePointImpl<>("P73", new double[]{21.2, 11.8}));
        add(new SpacePoint.SpacePointImpl<>("P74", new double[]{22.2, 12.4}));
        add(new SpacePoint.SpacePointImpl<>("P75", new double[]{32.3, 22.7}));
        add(new SpacePoint.SpacePointImpl<>("P76", new double[]{43.4, 23.2}));
        add(new SpacePoint.SpacePointImpl<>("P77", new double[]{54.5, 34.5}));
        add(new SpacePoint.SpacePointImpl<>("P78", new double[]{65.6, 45.9}));
        add(new SpacePoint.SpacePointImpl<>("P79", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P80", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P81", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P82", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P83", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P84", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P85", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P86", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P87", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P88", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P89", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P90", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P91", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P92", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P93", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P94", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P95", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P96", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P97", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P98", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P99", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P100", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P101", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P102", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P103", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P104", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P105", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P106", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P107", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P108", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P109", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P110", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P111", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P112", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P113", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P114", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P115", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P116", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P117", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P118", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P119", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P120", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P121", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P122", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P123", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P124", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P125", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P126", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P127", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P128", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P129", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P130", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P131", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P132", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P133", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P134", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P135", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P136", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P137", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P138", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P139", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P140", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P141", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P142", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P143", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P144", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P145", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P146", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P147", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P148", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P149", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P150", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P151", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P152", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P153", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P154", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P155", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P156", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P157", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P158", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P159", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P160", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P161", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P162", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P163", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P164", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P165", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P166", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P167", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P168", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P169", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P170", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P171", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P172", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P173", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P174", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P175", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P176", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P177", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P178", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P179", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P180", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P181", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P182", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P183", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P184", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P185", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P186", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P187", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P188", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P189", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P190", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P191", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P192", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P193", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P194", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P195", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P196", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P197", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P198", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P199", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P200", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P201", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P202", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P203", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P204", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P205", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P206", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P207", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P208", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P209", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P210", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P211", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P212", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P213", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P214", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P215", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P216", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P217", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P218", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P219", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P220", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P221", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P222", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P223", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P224", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P225", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P226", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P227", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P228", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P229", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P230", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P231", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P232", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P233", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P234", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P235", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P236", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P237", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P238", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P239", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P240", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P241", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P242", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P243", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P244", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P245", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P246", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P247", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P248", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P249", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P250", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P251", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P252", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P253", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P254", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P255", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P256", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P257", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P258", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P259", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P260", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P261", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P262", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P263", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P264", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P265", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P266", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P267", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P268", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P269", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P270", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P271", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P272", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P273", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P274", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P275", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P276", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P277", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P278", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P279", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P280", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P281", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P282", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P283", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P284", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P285", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P286", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P287", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P288", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P289", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P290", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P291", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P292", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P293", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P294", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P295", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P296", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P297", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P298", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P299", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P300", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P301", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P302", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P303", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P304", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P305", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P306", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P307", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P308", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P309", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P310", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P311", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P312", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P313", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P314", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P315", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P316", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P317", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P318", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P319", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P320", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P321", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P322", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P323", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P324", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P325", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P326", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P327", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P328", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P329", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P330", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P331", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P332", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P333", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P334", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P335", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P336", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P337", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P338", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P339", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P340", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P341", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P342", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P343", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P344", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P345", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P346", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P347", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P348", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P349", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P350", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P351", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P352", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P353", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P354", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P355", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P356", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P357", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P358", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P359", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P360", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P361", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P362", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P363", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P364", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P365", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P366", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P367", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P368", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P369", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P370", new double[]{607.6, 405.9}));
        add(new SpacePoint.SpacePointImpl<>("P371", new double[]{708.7, 506.3}));
        add(new SpacePoint.SpacePointImpl<>("P372", new double[]{708.7, 607.8}));
        add(new SpacePoint.SpacePointImpl<>("P373", new double[]{809.8, 707.2}));
        add(new SpacePoint.SpacePointImpl<>("P374", new double[]{801.8, 708.10}));
        add(new SpacePoint.SpacePointImpl<>("P375", new double[]{901.9, 808.6}));
        add(new SpacePoint.SpacePointImpl<>("P376", new double[]{102.1, 809.1}));
        add(new SpacePoint.SpacePointImpl<>("P377", new double[]{102.1, 901.4}));
        add(new SpacePoint.SpacePointImpl<>("P378", new double[]{203.2, 101.8}));
        add(new SpacePoint.SpacePointImpl<>("P379", new double[]{204.2, 102.4}));
        add(new SpacePoint.SpacePointImpl<>("P380", new double[]{305.3, 202.7}));
        add(new SpacePoint.SpacePointImpl<>("P381", new double[]{406.4, 203.2}));
        add(new SpacePoint.SpacePointImpl<>("P382", new double[]{507.5, 304.5}));
        add(new SpacePoint.SpacePointImpl<>("P383", new double[]{607.6, 405.9}));
    }};

    List<SpacePoint<String>> data2 = new ArrayList<>() {{
        add(new SpacePoint.SpacePointImpl<>("P01", new double[]{1, 3, 5}));
        add(new SpacePoint.SpacePointImpl<>("P02", new double[]{1, 8, 4}));
        add(new SpacePoint.SpacePointImpl<>("P03", new double[]{2, 2, 9}));
        add(new SpacePoint.SpacePointImpl<>("P04", new double[]{2, 10, 6}));
        add(new SpacePoint.SpacePointImpl<>("P05", new double[]{3, 6, 0}));
        add(new SpacePoint.SpacePointImpl<>("P06", new double[]{4, 1, 7}));
        add(new SpacePoint.SpacePointImpl<>("P07", new double[]{5, 4, 1}));
        add(new SpacePoint.SpacePointImpl<>("P08", new double[]{6, 8, 3}));
        add(new SpacePoint.SpacePointImpl<>("P09", new double[]{7, 4, 6}));
        add(new SpacePoint.SpacePointImpl<>("P10", new double[]{7, 7, 8}));
        add(new SpacePoint.SpacePointImpl<>("P11", new double[]{8, 2, 10}));
        add(new SpacePoint.SpacePointImpl<>("P12", new double[]{8, 5, 2}));
        add(new SpacePoint.SpacePointImpl<>("P13", new double[]{9, 9, 6}));
    }};

    @Test
    public void OcTreeDemo() {
        OcTree<String> ocTree = new OcTree<>(data2);
        SpacePoint<String> nearestPoint = ocTree.nearest(new SpacePoint.SpacePointImpl<>("B", new double[]{8, 6, 5}));
        System.out.println(nearestPoint);
        System.out.println(ocTree);

        FileUtils.toMdFile(TreeUtil.toMermaid(ocTree.getRoot(), (AbstractQuadOcTree.Node node) -> {
            List<AbstractQuadOcTree.Node> list = new LinkedList<>();
            if (node.getChild() == null) {
                return list;
            }
            for (AbstractQuadOcTree.Node n : node.getChild()) {
                list.add(n);
            }
            return list;
        }), "OcTreeDemo");
    }

    @Test
    public void QuadTreeDemo() {
        QuadTree<String> quadTree = new QuadTree<>(data);
        SpacePoint<String> nearestPoint = quadTree.nearest(new SpacePoint.SpacePointImpl<>("B", new double[]{6, 7.3}));
        System.out.println(nearestPoint);
        System.out.println("{6, 7.3} -> {6, 8} = " + Distance.EUCLIDEAN.getDistance(new double[]{6, 7.3}, new double[]{6, 8}));
        System.out.println("{6, 7.3} -> {7, 7} = " + Distance.EUCLIDEAN.getDistance(new double[]{6, 7.3}, new double[]{7, 7}));

        FileUtils.toMdFile(TreeUtil.toMermaid(quadTree.getRoot(), (AbstractQuadOcTree.Node node) -> {
            List<AbstractQuadOcTree.Node> list = new LinkedList<>();
            if (node.getChild() == null) {
                return list;
            }
            for (AbstractQuadOcTree.Node n : node.getChild()) {
                list.add(n);
            }
            return list;
        }), "QuadTreeDemo");
    }

    @Test
    public void BallTreeDemo() {
        String node = "B";
        double[] coord = new double[] {8, 4};
        data.add(new SpacePoint.SpacePointImpl<>(node, coord));
        SpacePoint<String> queryPoint = new SpacePoint.SpacePointImpl<>(node, coord);

        long[] time = new long[4];
        time[0] = System.currentTimeMillis();

        BallTree<String> ballTree = new BallTree<>(data);

        time[1] = System.currentTimeMillis();

        SpacePoint<String> nearestPoint = ballTree.nearest(queryPoint);

        time[2] = System.currentTimeMillis();

        List<SpacePoint<String>> rangPoint = ballTree.range(queryPoint, 4);

        time[3] = System.currentTimeMillis();

        System.out.println(Arrays.toString(time) + ", " + (time[1] - time[0]));

        System.out.println(nearestPoint);

        for (SpacePoint<String> temp : rangPoint) {
            System.out.println(temp.getPoint() + ": " + Arrays.toString(temp.getCoord()));
        }

        FileUtils.toMdFile(TreeUtil.toMermaid(ballTree.getRoot(), BallTree.Node::getChild), "BallTreeDemo");
    }

    @Test
    public void KDTreeDemo() {
        String node = "B";
        double[] coord = new double[] {8, 4};
        data.add(new SpacePoint.SpacePointImpl<>(node, coord));
        SpacePoint<String> queryPoint = new SpacePoint.SpacePointImpl<>(node, coord);

        long[] time = new long[4];
        time[0] = System.currentTimeMillis();

        KDTree<String> kDimensionalTree = new KDTree<>(data);

        time[1] = System.currentTimeMillis();

        SpacePoint<String> nearestPoint = kDimensionalTree.nearest(queryPoint);

        time[2] = System.currentTimeMillis();

        List<SpacePoint<String>> rangPoint = kDimensionalTree.range(queryPoint, 4);

        time[3] = System.currentTimeMillis();

        System.out.println(Arrays.toString(time) + ", " + (time[1] - time[0]));

        System.out.println(nearestPoint);

        for (SpacePoint<String> temp : rangPoint) {
            System.out.println(temp.getPoint() + ": " + Arrays.toString(temp.getCoord()));
        }

        FileUtils.toMdFile(TreeUtil.toMermaid(kDimensionalTree.getRoot(), KDTree.Node::getChild), "KDTreeDemo");
    }

    @Test
    public void dbscanDemo() {
        ClusteringAlgorithm<String> clustering = new DBSCAN<>(3, 2.5, data);
        // ClusteringAlgorithm<String> clustering = new DBSCAN<>(3, 2.5, data, new BallTree(data));
        ClusteringAlgorithm.Clustering<SpacePoint<String>> clusters = clustering.getClustering();
        System.out.println("the number of clusters: " + clusters.getNumberClusters());
        for (Set<SpacePoint<String>> list : clusters.getClusters()) {
            for (SpacePoint<String> temp : list) {
                System.out.print(temp.getPoint() + ", ");
            }
            System.out.println();
        }
    }

    @Test
    public void kSpanningTreeClusteringDemo() {
        Graph<String> graph = GraphBuilder.<String>undirected().build();

        String n1 = "n1";
        String n2 = "n2";
        String n3 = "n3";
        String n4 = "n4";
        String n5 = "n5";
        String n6 = "n6";
        String n7 = "n7";
        String n8 = "n8";
        String n9 = "n9";
        graph.addEdge(new Edge<>(n1, n2));
        graph.addEdge(new Edge<>(n1, n6));
        graph.addEdge(new Edge<>(n2, n3));
        graph.addEdge(new Edge<>(n3, n4));
        graph.addEdge(new Edge<>(n4, n5));
        graph.addEdge(new Edge<>(n5, n2));
        graph.addEdge(new Edge<>(n5, n1));
        graph.addEdge(new Edge<>(n6, n7));
        graph.addEdge(new Edge<>(n6, n8));
        graph.addEdge(new Edge<>(n8, n9));
        graph.addEdge(new Edge<>(n9, n6));

        ClusteringAlgorithm<String> clustering = new KSpanningTreeClustering(graph, 8);

        ClusteringAlgorithm.Clustering<String> clusters = clustering.getClustering();
        System.out.println("the number of clusters: " + clusters.getNumberClusters());
        for (Set<String> list : clusters.getClusters()) {
            System.out.println(list);
        }

        FileUtils.toMdFile(GraphUtil.toMermaid(graph), "kSpanningTreeClusteringDemo");
    }
}
