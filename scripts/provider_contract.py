"""Model the grant-uri-permission rule verified in AOSP Android 12 parsing code."""
import xml.etree.ElementTree as ET

ANDROID = '{http://schemas.android.com/apk/res/android}'
REFERENCE = ('https://github.com/aosp-mirror/platform_frameworks_base/blob/'
             'android-12.0.0_r1/core/java/android/content/pm/parsing/component/ParsedProviderUtils.java#L208-L270')


def inspect_provider(manifest):
    root = ET.parse(manifest).getroot()
    provider = next(p for p in root.findall('application/provider')
                    if p.get(ANDROID + 'name') == 'com.alphainventor.filemanager.provider.MyFileProvider')
    patterns = [{k.replace(ANDROID, ''): v for k, v in child.attrib.items()}
                for child in provider.findall('grant-uri-permission')]
    has_pattern = any(any(k in p for k in ('path', 'pathPrefix', 'pathPattern', 'pathAdvancedPattern', 'pathSuffix'))
                      for p in patterns)
    declared = provider.get(ANDROID + 'grantUriPermissions') == 'true'
    return {'declared_grant_uri_permissions': declared, 'grant_uri_permission_patterns': patterns,
            'effective_grant_expected_android12': declared or has_pattern,
            'exported': provider.get(ANDROID + 'exported'),
            'basis': 'AOSP Android 12 parser source; not a device observation', 'reference': REFERENCE}
